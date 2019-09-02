package com.tcl.demo.boot.dal.user.dataobject;

import lombok.Data;

import java.io.Serializable;

@Data
public class McAccountDataObject implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 账户编号
     */
    private String accountNo;

    /**
     * 账户类型
     */
    private Integer accountType;
}
