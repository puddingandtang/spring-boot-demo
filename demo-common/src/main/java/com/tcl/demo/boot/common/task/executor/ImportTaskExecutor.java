package com.tcl.demo.boot.common.task.executor;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.tcl.demo.boot.common.task.BaseTask;
import com.tcl.demo.boot.common.task.TaskThreadPoolExecutor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j(topic = "task")
public class ImportTaskExecutor {

    private static AtomicBoolean isClose = new AtomicBoolean(false);

    private static TaskThreadPoolExecutor executor = new TaskThreadPoolExecutor(60 * 1000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    static {

        isClose.set(true);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> closeAndWaitToWorkInfo()));
    }


    /**
     * 执行
     *
     * @param task
     * @return
     */
    public static Future processResultForFuture(BaseTask task) {

        if (isClose.get()) {
            log.info("");
            return null;
        }

        Future future = executor.submit(task);
        // 设置
        executor.getAllFuture().put(task.getTaskContext().getTaskNo(), future);

        return future;

    }


    /**
     * 取消
     *
     * @param taskNo
     * @return
     */
    public static boolean cancelForFuture(String taskNo, boolean mayInterruptIfRunning) {

        if (Strings.isNullOrEmpty(taskNo)) {

            return false;
        }

        Future future = executor.getAllFuture().get(taskNo);
        if (null == future) {

            return false;
        }

        if (future.isDone()) {

            return false;
        }

        return future.cancel(mayInterruptIfRunning);
    }

    /**
     * 关闭线程池
     */
    public static void closeAndWaitToWorkInfo() {

        if (executor.isShutdown()) {
            return;
        }

        try {
            executor.isShutdown();
        } catch (Exception e) {
            return;
        }

        try {
            if (!executor.awaitTermination(10000, TimeUnit.MILLISECONDS)) {
                List<Runnable> notProcessRunnable = executor.shutdownNow();

                // todo 记录到日志文件中，以便追溯notProcessRunnable
            }

        } catch (Exception e) {

            if (e instanceof InterruptedException) {
                executor.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }

    }

}
