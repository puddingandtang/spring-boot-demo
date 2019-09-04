package com.tcl.demo.boot.common.model.rule.activity.reward;

import lombok.Data;

import java.io.Serializable;

@Data
public class RewardContent implements Serializable {

    private static final long serialVersionUID = -3844162493103747417L;

    /**
     * 奖励类型
     */
    private Integer rewardType;

    /**
     * 奖励编号
     */
    private Long rewardNo;

    /**
     * 奖励数量
     */
    private Long rewardNum;

    /**
     * 奖励价值
     */
    private Long rewardWorth;


}
