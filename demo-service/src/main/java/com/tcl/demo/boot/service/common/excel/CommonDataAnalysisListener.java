package com.tcl.demo.boot.service.common.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommonDataAnalysisListener extends AnalysisEventListener<ExcelData> {


    @Override
    public void invoke(ExcelData data, AnalysisContext context) {

        log.info("CommonDataAnalysisListener-解析Excel，处理数据[{}]", JSON.toJSONString(data));
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        log.info("CommonDataAnalysisListener-doAfterAllAnalysed[{}]", JSON.toJSONString(context));

    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        super.onException(exception, context);
    }
}
