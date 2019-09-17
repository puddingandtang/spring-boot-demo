package com.tcl.demo.boot.common.task;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "task")
public class TaskThreadPoolExecutor extends ThreadPoolExecutor {

    /**
     * 存放线程的开始执行时间节点
     */
    private ConcurrentHashMap<String, Long> startTimes;

    private volatile ConcurrentHashMap<String, DoingRunning> workersDoing = new ConcurrentHashMap();

    @Override
    protected void beforeExecute(Thread t, Runnable r) {


        startTimes.put(String.valueOf(r.hashCode()), System.currentTimeMillis());

        BaseTask task = (BaseTask) r;
        TaskContext taskContext = task.getTaskContext();
        if (null != taskContext && !Strings.isNullOrEmpty(taskContext.getTaskNo())) {
            DoingRunning doingRunning = new DoingRunning(t, r);

            log.debug("设置-{},DoingRunning-开始", taskContext.getTaskNo());
            workersDoing.put(taskContext.getTaskNo(), doingRunning);
            log.debug("设置-{},DoingRunning-结束", taskContext.getTaskNo());
        }

        super.beforeExecute(t, r);


    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {


        long endTime = System.currentTimeMillis();

        // startTimes.remove ，避免 startTimes 过大，导致系统溢出
        long diff = endTime - startTimes.remove(String.valueOf(r.hashCode()));

        // 移除doingRunning
        BaseTask task = (BaseTask) r;
        TaskContext taskContext = task.getTaskContext();
        if (null != taskContext && !Strings.isNullOrEmpty(taskContext.getTaskNo())) {
            workersDoing.remove(taskContext.getTaskNo());
        }

        // 统计任务耗时、初始线程数、核心线程数、正在执行的任务数量、已完成任务数量、任务总数、队列里缓存的任务数量、池中存在的最大线程数、最大允许的线程数、线程空闲时间、线程池是否关闭、线程池是否终止
        log.info(String.format(
                "task-pool-monitor-%s: Duration: %d ms, PoolSize: %d, CorePoolSize: %d, Active: %d, Completed: %d, Task: %d, Queue: %d, LargestPoolSize: %d, MaximumPoolSize: %d,KeepAliveTime: %d, isShutdown: %s, isTerminated: %s",
                taskContext.getTaskNo(),
                diff,
                this.getPoolSize(), this.getCorePoolSize(), this.getActiveCount(), this.getCompletedTaskCount(), this.getTaskCount(),
                this.getQueue().size(), this.getLargestPoolSize(), this.getMaximumPoolSize(), this.getKeepAliveTime(TimeUnit.MILLISECONDS),
                this.isShutdown(), this.isTerminated()));


        super.afterExecute(r, t);

    }

    @Override
    public void shutdown() {
        // 统计已执行任务、正在执行任务、未执行任务数量
        log.info(String.format("task-pool-monitor, Going to shutdown. Executed tasks: %d, Running tasks: %d, Pending tasks: %d",
                this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size()));
        super.shutdown();
    }

    @Override
    public List<Runnable> shutdownNow() {

        log.info(
                String.format("task-pool-monitor, Going to immediately shutdown. Executed tasks: %d, Running tasks: %d, Pending tasks: %d",
                        this.getCompletedTaskCount(), this.getActiveCount(), this.getQueue().size()));

        return super.shutdownNow();
    }

    /**
     * 取消
     * {@link TaskThreadPoolExecutor#cancelTaskForBlock(java.lang.String)}
     * {@link TaskThreadPoolExecutor#cancelTaskForDoing(java.lang.String)}
     * <p>
     * 存在问题：
     * com.tcl.demo.boot.common.task.TaskThreadPoolExecutor#cancelTaskForDoing(java.lang.String) 依赖于workersDoing，数据的构建存在一个时间差
     * <p>
     * 如果不使用这个，可以使用{@link BaseTask#breakIfTaskInfoStop(com.tcl.demo.boot.common.task.TaskContext)},结合任务info的执行状态判断，子类自行实现
     *
     * @param taskNo
     * @return
     */
    public boolean cancel(final String taskNo) {

        if (Strings.isNullOrEmpty(taskNo)) {
            return false;
        }

        boolean cancel = cancelTaskForBlock(taskNo);

        log.info("取消-cancelTaskForBlock-{},结果：{}", taskNo, cancel);

        if (cancel) {
            return true;
        }

        cancel = cancelTaskForDoing(taskNo);

        // log.info("取消-cancelTaskForDoing-{},结果：{}", taskNo, cancel);

        return cancel;
        //boolean cancel = cancelTaskForBlock(taskNo);

        //return cancel ? true : cancelTaskForDoing(taskNo);
    }

    /**
     * 任务取消-阻塞队列
     *
     * @param taskNo
     */
    protected boolean cancelTaskForBlock(final String taskNo) {

        if (Strings.isNullOrEmpty(taskNo)) {
            return false;
        }

        boolean cancel = false;
        BlockingQueue<Runnable> queue = super.getQueue();
        Iterator<Runnable> iteratorBlockQ = queue.iterator();
        while (iteratorBlockQ.hasNext()) {
            BaseTask baseTask = (BaseTask) iteratorBlockQ.next();
            if (null != baseTask.getTaskContext() && null != baseTask.getTaskContext().getTaskNo()) {
                if (baseTask.getTaskContext().getTaskNo().equalsIgnoreCase(taskNo)) {

                    cancel = super.remove(baseTask);
                    log.info("移除任务编号-阻塞队列-队列大小：{}，移除任务编号：{}，移除结果：{}", this.getQueue().size(), taskNo, cancel);
                    break;
                }
            }
        }

        return cancel;

    }

    /**
     * 任务取消-当前正在执行
     *
     * @param taskNo
     * @return
     */
    protected boolean cancelTaskForDoing(final String taskNo) {

        if (Strings.isNullOrEmpty(taskNo)) {
            return false;
        }

        DoingRunning doingRunning = workersDoing.get(taskNo);

        if (null == doingRunning) {
            // 执行到此处，可能workersDoing还未构建完成。。。。。。
            // java.util.concurrent.ThreadPoolExecutor.Worker.run，这里是异步的，构建workersDoing是在里面
            // 目前建议业务重试
            return false;
        }

        Thread curThread = doingRunning.getThread();
        BaseTask curTask = (BaseTask) doingRunning.getRunnable();
        if (null == curTask || null == curTask.getTaskContext() || Strings.isNullOrEmpty(curTask.getTaskContext().getTaskNo())) {

            return false;
        }

        String curTaskNo = curTask.getTaskContext().getTaskNo();
        if (taskNo.equalsIgnoreCase(curTaskNo)) {
            // 设置中断位
            curThread.interrupt();
            log.info("移除任务编号-执行队列{}：{}，移除结果：{}", curThread, taskNo, true);
            return true;
        }

        return false;
    }

    /**
     * 正在运行的
     */
    private final class DoingRunning {

        private Thread thread;

        private Runnable runnable;

        public DoingRunning(Thread thread, Runnable runnable) {
            this.thread = thread;
            this.runnable = runnable;
        }

        public Thread getThread() {
            return thread;
        }

        public void setThread(Thread thread) {
            this.thread = thread;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public void setRunnable(Runnable runnable) {
            this.runnable = runnable;
        }
    }

    /**************************************  构造函数,如有其他需要自行添加  *************************************************************/

    public TaskThreadPoolExecutor(
            long keepAliveTime,
            TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {

        super(Runtime.getRuntime().availableProcessors() * 2,
                Runtime.getRuntime().availableProcessors() * 2,
                keepAliveTime,
                unit,
                workQueue);

        this.startTimes = new ConcurrentHashMap<>();

    }
}
