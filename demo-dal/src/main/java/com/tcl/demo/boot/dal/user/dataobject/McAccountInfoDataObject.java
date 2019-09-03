package com.tcl.demo.boot.dal.user.dataobject;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 营销用户体系-账户表
 * 内部账户表，已和外部用户无关在
 * <p>
 * 后期账户表的分库分表根据{@link McAccountInfoDataObject#identityNo}，这样可以是的同一个身份的的多账户落在同一个区域
 */
@Data
public class McAccountInfoDataObject implements Serializable {

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 营销用户体系-身份编号
     * <p>
     * {@link McIdentityInfoDataObject#identityNo}
     */
    private String identityNo;

    /**
     * 营销业务账户编号
     */
    private String accountNo;

    /**
     * 账户类型：1.券账户，2.积分账户，3.卡账户
     */
    private Integer accountType;

    /**
     * 账户状态：1.待激活，2.已激活，3.已冻结，9.已注销
     */
    private Integer status;

    /**
     * 扩展字段
     */
    private String extendField;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;
}
