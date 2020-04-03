package com.tcl.demo.boot.service.test.design;

import lombok.Data;

import java.io.Serializable;

@Data
public class Entry implements Serializable {

    /**
     * 类型：
     * 1.笔记本电脑
     * 2.台式机
     */
    private Integer type;

    /**
     * 编码
     */
    private String no;
}
