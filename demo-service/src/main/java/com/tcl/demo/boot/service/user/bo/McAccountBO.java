package com.tcl.demo.boot.service.user.bo;

import com.tcl.demo.boot.dal.user.dataobject.McIdentityInfoDataObject;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class McAccountBO implements Serializable {

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


}
