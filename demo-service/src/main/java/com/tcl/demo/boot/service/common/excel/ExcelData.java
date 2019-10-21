package com.tcl.demo.boot.service.common.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * excel数据模型
 * <p>
 * 目前仅仅支持20列
 */
public class ExcelData implements Serializable {

    @ExcelProperty(index = 0)
    @JSONField(name = "0")
    private String result;

    @ExcelProperty(index = 1)
    @JSONField(name = "1")
    private String column1;

    @ExcelProperty(index = 2)
    @JSONField(name = "2")
    private String column2;

    @ExcelProperty(index = 3)
    @JSONField(name = "3")
    private String column3;

    @ExcelProperty(index = 4)
    @JSONField(name = "4")
    private String column4;

    @ExcelProperty(index = 5)
    @JSONField(name = "5")
    private String column5;

    @ExcelProperty(index = 6)
    @JSONField(name = "6")
    private String column6;

    @ExcelProperty(index = 7)
    @JSONField(name = "7")
    private String column7;

    @ExcelProperty(index = 8)
    @JSONField(name = "8")
    private String column8;

    @ExcelProperty(index = 9)
    @JSONField(name = "9")
    private String column9;

    @ExcelProperty(index = 10)
    @JSONField(name = "10")
    private String column10;

    @ExcelProperty(index = 11)
    @JSONField(name = "11")
    private String column11;

    @ExcelProperty(index = 12)
    @JSONField(name = "12")
    private String column12;

    @ExcelProperty(index = 13)
    @JSONField(name = "13")
    private String column13;

    @ExcelProperty(index = 14)
    @JSONField(name = "14")
    private String column14;

    @ExcelProperty(index = 15)
    @JSONField(name = "15")
    private String column15;

    @ExcelProperty(index = 16)
    @JSONField(name = "16")
    private String column16;

    @ExcelProperty(index = 17)
    @JSONField(name = "17")
    private String column17;

    @ExcelProperty(index = 18)
    @JSONField(name = "18")
    private String column18;

    @ExcelProperty(index = 19)
    @JSONField(name = "19")
    private String column19;

    @ExcelProperty(index = 20)
    @JSONField(name = "20")
    private String column20;

}
