package com.tcl.demo.boot.common.test.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class DemoData implements Serializable {

    @ExcelProperty(value = "用户手机号")
    private String userPhone;

    @ExcelProperty(value = "用户编号")
    private Long userNo;

    @ExcelProperty(value = "是否成功")
    private Boolean isSuccess;

    @ExcelProperty(value = "失败原因")
    private String failMsg;
}
