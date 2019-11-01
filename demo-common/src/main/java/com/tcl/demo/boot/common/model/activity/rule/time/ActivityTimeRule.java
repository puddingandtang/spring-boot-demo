package com.tcl.demo.boot.common.model.activity.rule.time;

import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityTimeRule extends BaseRule implements Serializable {

    /**
     * 活动开始时间
     */
    private Long begin;

    /**
     * 活动结束时间
     */
    private Long end;

    /**
     * 一天：0点～24点
     */
    private List<TimeFrame> times;

    /**
     * 一周：星期一 ～ 星期天
     */
    private List<Integer> weeks;

    /**
     * 一个月份：1号 ～ 31 号
     */
    private List<Integer> days;

    public ActivityTimeRule() {
        super(RuleEnum.ACTIVITY_TIME_RULE.getRuleCode(), "1.0", true);
    }
}
