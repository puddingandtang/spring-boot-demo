package com.tcl.demo.boot.common.task;

import com.google.common.base.Strings;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import static com.tcl.demo.boot.common.base.ErrorCodes.TASK_COMMON_ERROR;

@Slf4j(topic = "task")
public abstract class BaseTask<T> implements Runnable {

    @Setter
    @Getter
    private TaskContext<T> taskContext;

    public BaseTask(TaskContext<T> taskContext) {
        this.taskContext = taskContext;
    }

    @Override
    public void run() {

        try {

            this.processCoreBefore(taskContext);

            this.processCore(taskContext);

            this.processCoreAfter(taskContext);

        } catch (Exception e) {

            if (e instanceof InterruptedException) {
                log.info("1");

            } else {
                log.error("2");
            }

            this.updateTaskInfo(taskContext);
        }


    }

    /**
     * 前置执行
     *
     * @param taskContext
     */
    protected void processCoreBefore(TaskContext<T> taskContext) {

        PreconditionsAssert.assertNotNull(taskContext, TASK_COMMON_ERROR, "TaskContext is not exist");
        PreconditionsAssert.assertNotNull(taskContext.getData(), TASK_COMMON_ERROR, "TaskContext#Data is not exist");
        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(taskContext.getTaskNo()), TASK_COMMON_ERROR, "TaskContext#TaskNo is not exist");

    }

    /**
     * 核心执行，请关注
     */
    protected abstract void processCore(TaskContext<T> taskContext);

    protected void processCoreAfter(TaskContext<T> taskContext) {

    }

    /**
     * 更新任务信息
     */
    protected void updateTaskInfo(TaskContext<T> taskContext) {

        throw new BizNormalException(TASK_COMMON_ERROR, "子类实现");
    }

    /**
     * 设置取消正在执行-设置中断标志
     */
    protected void cancleDoing() {

        Thread.currentThread().interrupt();
    }
}
