package com.tcl.demo.boot.service.common.processor.rule;

import com.google.common.base.Optional;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.model.rule.RuleEnum;
import com.tcl.demo.boot.common.model.rule.coupon.CouponCityRule;
import com.tcl.demo.boot.common.model.rule.coupon.CouponCityTypeEnum;
import com.tcl.demo.boot.service.common.processor.RuleVerifyCondition;
import com.tcl.demo.boot.service.common.processor.RuleVerifyContent;
import com.tcl.demo.boot.service.common.processor.RuleVerifyProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CityRuleVerifyProcessor extends RuleVerifyProcessor {

    @Override
    protected boolean verifyProcess(RuleVerifyCondition condition, RuleVerifyContent content) {

        CouponCityRule rule = (CouponCityRule) content.getRules().get(RuleEnum.COUPON_CITY_RULE.getRuleCode());

        // 规则存在性
        if (null == rule) {

            log.info("券规则校验-城市规则-规则不存在，视为校验不通过");
            content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_EXIST_ERROR, "券城市规则"));
            return false;
        }

        CouponCityTypeEnum typeEnum = CouponCityTypeEnum.acquireByType(rule.getType());

        // 规则作用类型
        if (null == typeEnum) {

            log.info("券规则校验-城市规则-规则类型不存在，视为校验不通过");
            content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券城市规则"));
            return false;
        }

        if (null == condition || Strings.isNullOrEmpty(condition.getCityCode())) {

            log.info("券规则校验-城市规则-规则类型不存在，视为校验不通过");

            return false;
        }

        String outCity = condition.getCityCode();

        switch (typeEnum) {
            case ALL_CITY: {

                log.info("券规则校验-城市规则-规则全国，校验通过");
                return true;
            }
            case USABLE_CITY: {

                List<String> configCities = Optional.fromNullable(rule.getCityCodes()).or(Lists.newArrayList());

                // 匹配上则说明pass
                boolean matchResult = configCities.contains(outCity);
                log.info("券规则校验-城市规则-规则可用城市，校验[{}]", matchResult);

                if (!matchResult) {
                    content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券城市规则"));
                }

                return matchResult;
            }
            case DISABLE_CITY: {

                List<String> configCities = Optional.fromNullable(rule.getCityCodes()).or(Lists.newArrayList());
                // 匹配上则说明fail
                boolean matchResult = configCities.contains(outCity);
                log.info("券规则校验-城市规则-规则可用城市，校验[{}]", matchResult);

                if (matchResult) {
                    content.setCode(super.buildErrorMsg(ErrorCodes.RULE_NOT_MATCH_ERROR, "券城市规则"));
                }

                return !matchResult;

            }
            default: {

                return false;
            }
        }
    }


}
