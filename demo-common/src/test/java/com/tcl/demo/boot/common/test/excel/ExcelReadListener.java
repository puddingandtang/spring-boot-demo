package com.tcl.demo.boot.common.test.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExcelReadListener extends AnalysisEventListener<DemoData> {

    @Override
    public void invoke(DemoData data, AnalysisContext context) {

        log.info("invoke,{},{}", data, context);

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {


        log.info("doAfterAllAnalysed,{}", context);
    }
}
