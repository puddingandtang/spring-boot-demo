package com.tcl.demo.boot.common.model.rule.activity.reward;

import com.tcl.demo.boot.common.model.rule.BaseRule;
import com.tcl.demo.boot.common.model.rule.RuleEnum;
import com.tcl.demo.boot.common.model.rule.base.NotifyConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RewardMatchingRule extends BaseRule implements Serializable {

    /**
     * 匹配节点
     * {@link RewardMatchNode#getType()}
     */
    private Integer nodeMatchType;

    /**
     * 规则排序
     */
    private Integer sort;

    /**
     * 奖励阶梯类型
     */
    private RewardMatchNodeLadder ladder;

    /**
     * 奖励内容
     */
    private List<RewardContent> rewardContents;

    /**
     * 通知配置
     */
    private NotifyConfig notifyConfig;

    public RewardMatchingRule() {

        super(RuleEnum.ACTIVITY_REWARD_MATCHING_RULE.getRuleCode(), "1.0", true);
    }
}
