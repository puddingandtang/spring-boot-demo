package com.tcl.demo.boot.dal.user.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销用户体系-身份表
 * 解耦用户体系，构建属于营销自身用户体系，使得账户独立
 */
@Data
public class McIdentityInfoDataObject implements Serializable {

    /**
     * 主键id
     */
    private Long id;

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

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
