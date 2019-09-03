package com.tcl.demo.boot.service.user.bo;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class McIdentityBO implements Serializable {

    /**
     * 外部用户编号
     */
    private String userNo;

    /**
     * 外部用户类型
     */
    private Integer userType;

    /**
     * 营销用户体系-身份编号
     */
    private String identityNo;

    /**
     * 营销用户体系-身份编号状态
     */
    private Integer status;

}
