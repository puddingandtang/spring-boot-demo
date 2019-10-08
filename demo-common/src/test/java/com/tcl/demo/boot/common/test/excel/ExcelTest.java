package com.tcl.demo.boot.common.test.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.tcl.demo.boot.common.test.excel.converter.CustomStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

@Slf4j
public class ExcelTest {

    private static final String TEST_READ_PATH = "/Users/mr.tang/Documents/work/tangWork/testFolder";

    @Test
    public void simpleRead() {

        String filePath = TEST_READ_PATH + File.separator + "批量发放券-用户编号.xlsx";

        log.info("测试文件路径：{}", filePath);

        File file = new File(filePath);
        boolean isExist = file.exists();

        log.info("测试文件路径:{}，是否存在:{}", filePath, isExist);
        Assert.assertTrue("测试文件不存在", isExist);

        ExcelReader excelReader = EasyExcel.read(file, DemoData.class, new ExcelReadListener()).build();

        ReadSheet excelSheet = EasyExcel.readSheet(0).registerConverter(new CustomStringConverter()).build();
        excelReader.read(excelSheet);
        // excelReader.analysisContext();
        excelReader.finish();
    }

    @Test
    public void simpleWrite() {

        String filePath = TEST_READ_PATH + File.separator + "批量发放券-用户编号.xlsx";

        log.info("测试文件路径：{}", filePath);

        File file = new File(filePath);
        boolean isExist = file.exists();

        log.info("测试文件路径:{}，是否存在:{}", filePath, isExist);
        Assert.assertTrue("测试文件不存在", isExist);

        List<DemoData> input = Lists.newArrayList();
        ExcelWriter excelWriter = EasyExcel.write(file, DemoData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet(3).build();

        excelWriter.write(input, writeSheet);
        excelWriter.write(input, writeSheet);

        excelWriter.finish();
    }
}
