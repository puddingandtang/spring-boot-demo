package com.tcl.demo.boot.service.activity.context;

import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.tool.CollectionTool;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * 活动链路上下文
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTraceContext implements Serializable {

    /**
     * 活动上下文
     */
    private ActivityContext activity;

    /**
     * 活动参与上下文
     */
    private ActivityParticipantContext participant;

    /**
     * 中断标识
     */
    private boolean interrupt = false;

    /**
     * 获取活动规则
     *
     * @param ruleCode
     * @return
     */
    public BaseRule acquireByRuleCode(String ruleCode) {

        if (null == this.activity || CollectionTool.isEmpty(this.getActivity().getActivityRules())) {

            return null;
        }

        Map<String, BaseRule> activityRules = this.getActivity().getActivityRules();


        return activityRules.get(ruleCode);
    }

}
