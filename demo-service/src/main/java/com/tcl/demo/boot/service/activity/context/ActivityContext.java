package com.tcl.demo.boot.service.activity.context;

import com.tcl.demo.boot.common.model.BaseRule;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 活动信息上下文
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityContext implements Serializable {

    /**
     * 活动编号
     */
    private Long activityId;

    /**
     * 活动开始
     */
    private Date begin;

    /**
     * 活动时间
     */
    private Date end;

    /**
     * 活动规则
     */
    private Map<String, BaseRule> activityRules;
}
