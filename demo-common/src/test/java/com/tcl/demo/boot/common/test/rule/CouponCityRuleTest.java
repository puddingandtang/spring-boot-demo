package com.tcl.demo.boot.common.test.rule;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.model.coupon.rule.CouponCityRule;
import com.tcl.demo.boot.common.model.coupon.type.CouponLimitTypeEnum;
import org.junit.Ignore;
import org.junit.Test;

public class CouponCityRuleTest {

    @Test
    @Ignore
    public void cityCase1() {


        CouponCityRule couponCityRule = new CouponCityRule();
        couponCityRule.setVersion("2.0");
        couponCityRule.setType(CouponLimitTypeEnum.USABLE_LIMIT.getType());
        couponCityRule.setCityCodes(Lists.newArrayList("0571", "0572"));

        String data2JsonStr = JSON.toJSONString(couponCityRule);
        System.out.println(data2JsonStr);

        CouponCityRule jsonStr2Data = JSON.parseObject(data2JsonStr, CouponCityRule.class);

        System.out.println(jsonStr2Data);

        System.out.println(jsonStr2Data.getVersion());
    }


    public static void main(String[] args) {

        String regx = "^[0-9]{11}$";
        String a1 = "13065701233";
        String a2 = "13065701234";
        String a3 = "13065702235";
        String a4 = "18758174511";
        String a5 = "13065702230";
        String a6 = "15355710208";

        System.out.println(a6.matches(regx));




    }
}
