package com.tcl.demo.boot.service.user.bo;

import com.tcl.demo.boot.dal.user.type.McAccountTypeEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import com.tcl.demo.boot.dal.user.type.McValidConfigEnum;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class McIdentityAccountConfigBO implements Serializable {


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
}
