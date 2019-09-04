package com.tcl.demo.boot.common.test.rule;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.model.rule.coupon.CouponCityRule;
import com.tcl.demo.boot.common.model.rule.coupon.CouponCityTypeEnum;
import org.junit.Ignore;
import org.junit.Test;

public class CouponCityRuleTest {

    @Test
    @Ignore
    public void cityCase1() {


        CouponCityRule couponCityRule = new CouponCityRule();
        couponCityRule.setVersion("2.0");
        couponCityRule.setType(CouponCityTypeEnum.USABLE_CITY.getType());
        couponCityRule.setCityCodes(Lists.newArrayList("0571", "0572"));

        String data2JsonStr = JSON.toJSONString(couponCityRule);
        System.out.println(data2JsonStr);

        CouponCityRule jsonStr2Data = JSON.parseObject(data2JsonStr, CouponCityRule.class);

        System.out.println(jsonStr2Data);

        System.out.println(jsonStr2Data.getVersion());
    }
}
