package com.tcl.demo.boot.common.model.rule.coupon;

import com.tcl.demo.boot.common.model.rule.BaseRule;
import com.tcl.demo.boot.common.model.rule.RuleEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CouponTerminalRule  extends BaseRule implements Serializable {

    private static final long serialVersionUID = 7618716737141032631L;

    /**
     * 规则类型
     * {@link CouponCityTypeEnum#getType()}
     */
    private Integer type;

    /**
     * 城市编码
     */
    private List<String> cityCodes;

    public CouponTerminalRule() {

        super(RuleEnum.COUPON_CITY_RULE.getRuleCode(), "1.0", true);
    }
}