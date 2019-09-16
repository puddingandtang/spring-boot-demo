package com.tcl.demo.boot.service.test.task;

import com.tcl.demo.boot.common.task.BaseTask;
import com.tcl.demo.boot.common.task.TaskContext;
import com.tcl.demo.boot.service.coupon.task.entry.ImportCouponEntry;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "task")
public class ImportCouponDemoTask extends BaseTask<ImportCouponEntry> {

    @Override
    protected void processCore(TaskContext<ImportCouponEntry> taskContext) {
        String taskNo = taskContext.getTaskNo();
        try {


            log.info("执行任务开始：{}", taskNo);

            while (true) {

                if (Thread.currentThread().isInterrupted()) {
                    log.info("执行任务:{},检测到中断标志，进行中断处理", taskNo);
                    Thread.currentThread().interrupt();
                    break;
                } else {
                    // log.info("执行任务:{},正常结束", taskNo);

                    continue;
                }

            }

        } catch (Exception e) {

            log.error("执行任务：" + taskContext.getTaskNo() + ",异常：", e);
        }finally {
            log.info("执行任务结束：{}", taskNo);
        }


    }

    public ImportCouponDemoTask(ImportCouponEntry entry, String taskNo) {

        super(new TaskContext<>());
        super.getTaskContext().setTaskNo(taskNo);
        super.getTaskContext().setData(entry);

    }

}
