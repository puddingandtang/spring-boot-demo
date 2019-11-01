package com.tcl.demo.boot.common.model.coupon.rule;

import com.tcl.demo.boot.common.model.BaseRule;
import com.tcl.demo.boot.common.model.RuleEnum;
import com.tcl.demo.boot.common.model.coupon.type.CouponLimitTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CouponCityRule extends BaseRule implements Serializable {

    private static final long serialVersionUID = 7618716737141032631L;

    /**
     * 规则类型
     * {@link CouponLimitTypeEnum#getType()}
     */
    private Integer type;

    /**
     * 城市编码
     */
    private List<String> cityCodes;

    public CouponCityRule() {

        super(RuleEnum.COUPON_CITY_RULE.getRuleCode(), "1.0", true);
    }
}
