package com.tcl.demo.boot.dal.user.dataobject;

import com.tcl.demo.boot.dal.user.type.McAccountTypeEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import com.tcl.demo.boot.dal.user.type.McValidConfigEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class McIdentityAccountConfigDataObject implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * {@link McUserTypeEnum}
     */
    private Integer userType;

    /**
     * {@link McAccountTypeEnum}
     */
    private Integer accountType;

    /**
     * 是否初始化
     * {@link McValidConfigEnum}
     */
    private Integer defaultInit;

    /**
     * 状态
     * {@link McValidConfigEnum}
     */
    private Integer status;

    private Date createTime;

    private Date updateTime;
}
