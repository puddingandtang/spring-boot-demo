package com.tcl.demo.boot.service.coupon.task;

import com.tcl.demo.boot.common.config.ServiceFactory;
import com.tcl.demo.boot.common.task.BaseTask;
import com.tcl.demo.boot.common.task.TaskContext;
import com.tcl.demo.boot.common.task.TaskThreadPoolExecutor;
import com.tcl.demo.boot.common.tool.SnowflakeIdTool;
import com.tcl.demo.boot.service.coupon.task.entry.ImportCouponEntry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j(topic = "task")
public class ImportCouponTask extends BaseTask<ImportCouponEntry> {

    @Override
    protected void processCore(TaskContext<ImportCouponEntry> taskContext) {

        try {

            String taskNo = taskContext.getTaskNo();
            log.info("执行任务:{}开始", taskNo);

            while (true) {

                if ("TEST_DOING_CANCEL".equalsIgnoreCase(taskNo)) {
                    if (Thread.currentThread().isInterrupted()) {
                        log.info("执行任务:{},检测到中断标志，进行中断处理", taskNo);
                        Thread.currentThread().interrupt();
                        break;
                    }

                } else {
                    Thread.sleep(1000);
                    break;
                }
            }

            log.info("执行任务:{}结束", taskNo);

        } catch (Exception e) {

            log.error("执行任务：" + taskContext.getTaskNo() + ",异常：", e);
        }


    }


    public ImportCouponTask(ImportCouponEntry entry) {

        super(new TaskContext<>());
        String taskNo = "IMC" + SnowflakeIdTool.nextId();
        super.getTaskContext().setTaskNo(taskNo);
        super.getTaskContext().setData(entry);

        // todo 获取容器bean
        // this.xx = ServiceFactory.getBeanByName();

    }

    public ImportCouponTask(ImportCouponEntry entry, String taskNo) {

        super(new TaskContext<>());
        super.getTaskContext().setTaskNo(taskNo);
        super.getTaskContext().setData(entry);

        // todo 获取容器bean
        // this.xx = ServiceFactory.getBeanByName();

    }


    public static void main(String[] args) {

        String mock = "TEST_DOING_CANCEL";
        TaskThreadPoolExecutor executor = new TaskThreadPoolExecutor(60000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        List<String> taskNos = Lists.newArrayList();
        for (int i = 0; i < 20; i++) {

            String taskNo = "IMC" + SnowflakeIdTool.nextId();
            if (i == 0) {
                taskNo = mock;
            }

            log.info("构建任务Id:{}", taskNo);
            taskNos.add(taskNo);
            ImportCouponTask importCouponTask = new ImportCouponTask(new ImportCouponEntry(), taskNo);

            executor.execute(importCouponTask);
        }


        for (int idx = 0; idx < taskNos.size(); idx++) {

            String taskNo = taskNos.get(idx);
            if (taskNo.equalsIgnoreCase(mock)) {
                executor.cancelTask(taskNos.get(idx));
                break;
            }

//            if (idx % 2 == 0) {
//                log.info("移除任务Id:{}", taskNo);
//                executor.cancelTask(taskNos.get(idx));
//            }

        }

        while (true) {
        }


    }

}
