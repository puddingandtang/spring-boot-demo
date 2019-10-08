package com.tcl.demo.boot.common.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.tcl.demo.boot.common.test.excel.converter.CustomStringConverter;
import lombok.Data;

import java.io.Serializable;

@Data
public class DemoData implements Serializable {

    @ExcelProperty(value = "用户手机号", converter = CustomStringConverter.class)
    private String userPhone;

    @ExcelProperty(value = "用户编号")
    private Long userNo;

    @ExcelProperty(value = "是否成功")
    private Boolean isSuccess;

    @ExcelProperty(value = "失败原因")
    private String failMsg;

    @ExcelProperty(value = "失败原因1")
    private String failMsg1;

    @ExcelProperty(value = "失败原因2")
    private String failMsg2;

    @ExcelProperty(value = "失败原因3")
    private String failMsg3;
}
