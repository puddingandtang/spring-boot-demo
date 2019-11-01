package com.tcl.demo.boot.common.model.activity.rule.city;

import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.activity.type.ActivityLimitEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.HashSet;

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityCityRule extends BaseRule implements Serializable {

    /**
     * 限制类型
     * {@link ActivityLimitEnum}
     */
    private Integer limitType;

    /**
     * 城市编号
     */
    private HashSet<String> cityCodes;

    public ActivityCityRule() {
        super(RuleEnum.ACTIVITY_CITY_RULE.getRuleCode(), "1.0", true);
    }

}
