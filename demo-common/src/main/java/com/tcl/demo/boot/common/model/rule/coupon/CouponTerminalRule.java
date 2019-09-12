package com.tcl.demo.boot.common.model.rule.coupon;

import com.tcl.demo.boot.common.model.rule.BaseRule;
import com.tcl.demo.boot.common.model.rule.RuleEnum;
import com.tcl.demo.boot.common.model.rule.coupon.type.CouponLimitTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class CouponTerminalRule extends BaseRule implements Serializable {

    private static final long serialVersionUID = 7618716737141032631L;

    /**
     * 规则类型
     * {@link CouponLimitTypeEnum#getType()}
     */
    private Integer type;

    /**
     * 终端编号
     */
    private List<Integer> terminals;

    public CouponTerminalRule() {

        super(RuleEnum.COUPON_TERMINAL_RULE.getRuleCode(), "1.0", true);
    }
}