package com.tcl.demo.boot.service.test.task;

import com.tcl.demo.boot.common.task.TaskThreadPoolExecutor;
import com.tcl.demo.boot.common.tool.SnowflakeIdTool;
import com.tcl.demo.boot.service.coupon.task.entry.ImportCouponEntry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ImportCouponDemoTaskTest {

    @Test
    public void cancelTest() {

        TaskThreadPoolExecutor executor = new TaskThreadPoolExecutor(60000, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());

        List<String> taskNos = Lists.newArrayList();
        for (int i = 0; i < 15; i++) {
            try {

                String taskNo = "IMC" + SnowflakeIdTool.nextId();

                log.info("构建任务Id:{}", taskNo);
                taskNos.add(taskNo);
                ImportCouponDemoTask importCouponTask = new ImportCouponDemoTask(new ImportCouponEntry(), taskNo);

                executor.execute(importCouponTask);

                // 如果提交让线程先执行一段时间
                // Thread.sleep(10);

            } catch (Exception e) {
            }
        }


        for (int idx = 0; idx < taskNos.size(); idx++) {

            String taskNo = taskNos.get(idx);
            boolean cancel = executor.cancel(taskNo);
            log.info("操作取消：{},操作结果：{}", taskNo, cancel);

//            int tries = 3;
//
//            while (!cancel && tries-- > 0){
//
//                cancel = executor.cancel(taskNo);
//                log.info("操作取消-重试：{},操作结果：{}", taskNo, cancel);
//
//            }
        }

        while (true) {
        }


    }
}
