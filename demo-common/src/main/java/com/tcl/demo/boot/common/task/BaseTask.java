package com.tcl.demo.boot.common.task;

import com.google.common.base.Strings;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
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

                log.info("任务ID-{}中断异常:{}", taskContext.getTaskNo(), e);

            } else if (e instanceof BizRuntimeException) {

                log.info("任务ID-{}业务异常:{}", taskContext.getTaskNo(), e);

            } else {

                log.info("任务ID-" + taskContext.getTaskNo() + "异常:", e);
            }

            try {

                this.updateTaskInfo(taskContext);

            } catch (Exception e1) {

                log.error("任务ID-" + taskContext.getTaskNo() + "更新任务状态信息异常：", e1.getMessage());
            }


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
     *
     * @param taskContext
     */
    protected abstract void processCore(TaskContext<T> taskContext);

    protected void processCoreAfter(TaskContext<T> taskContext) {

        throw new BizNormalException(TASK_COMMON_ERROR, "子类实现");
    }

    /**
     * 更新任务信息
     *
     * @param taskContext
     */
    protected void updateTaskInfo(TaskContext<T> taskContext) {

        throw new BizNormalException(TASK_COMMON_ERROR, "子类实现");
    }

    /**
     * 判断任务状态是否stop
     *
     * @param taskContext
     * @return
     */
    protected boolean breakIfTaskInfoStop(TaskContext<T> taskContext) {
        // 查询TaskInfo的任务记录，判断当前状态是否执行取消task_status
        // 子类自行实现
        return false;
    }
}
