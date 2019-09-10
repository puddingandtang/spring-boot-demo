package com.tcl.demo.boot.service.common.command;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.service.common.processor.BizProcessKey;
import com.tcl.demo.boot.service.common.processor.rule.RuleVerifyCondition;
import com.tcl.demo.boot.service.common.processor.rule.RuleVerifyContent;
import com.tcl.demo.boot.service.coupon.CouponBO;
import com.tcl.demo.boot.service.coupon.CouponFilterBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Component
public class BestCouponRecommendCommand extends BaseCommand
        <BestRecommendCondition<CouponFilterBO>, BestRecommendContent<List<CouponBO>>> {

    @Resource
    private CouponRuleFilterTool couponRuleFilterTool;

    @Override
    protected void processCommandBefore(BestRecommendCondition<CouponFilterBO> condition, BestRecommendContent<List<CouponBO>> content) {


        CouponFilterBO couponFilterBO = condition.getCondition();
        List<CouponBO> commandData = content.getCommandData();

        if (null == couponFilterBO || CollectionTool.isEmpty(commandData)) {

            log.info("最优推荐-CouponFilterBO || List<CouponBO> 不存在，设置中断标志，处理结果设置空集合");
            content.setInterrupt(true);
            content.setResultData(Lists.newArrayList());
            return;
        }

        // 数据可用性校验 && 过滤
        final RuleVerifyCondition<CouponFilterBO> verifyCondition = new RuleVerifyCondition();
        verifyCondition.setVerifyCondition(couponFilterBO);
        List<CouponBO> availableCoupons = commandData.stream().filter(t -> {
            RuleVerifyContent<CouponBO> verifyContent = new RuleVerifyContent();
            verifyContent.setVerifyContent(t);
            return couponRuleFilterTool.baseRuleVerify(verifyCondition, verifyContent)
                    && couponRuleFilterTool.customRuleVerify(verifyCondition, verifyContent);
        }).collect(Collectors.toList());

        if (CollectionTool.isEmpty(availableCoupons)) {

            log.info("最优推荐-可用券数据为空，设置中断标志，处理结果设置空集合");
            content.setInterrupt(true);
            content.setResultData(Lists.newArrayList());
            return;
        }

        // 设置过程数据-可用券
        content.getProcessData().put(BizProcessKey.BEST_COUPON_FILTER_AVAILABLE.getKey(), availableCoupons);

    }

    @Override
    protected void processCommandDoing(BestRecommendCondition<CouponFilterBO> condition, BestRecommendContent<List<CouponBO>> content) {

        List<CouponBO> availableCoupons = (List<CouponBO>) content.getProcessData().get(BizProcessKey.BEST_COUPON_FILTER_AVAILABLE.getKey());
        CouponBO best = null;

        for (CouponBO curCoupon : availableCoupons) {

            if (null == best) {
                best = curCoupon;
                continue;
            }

            // 比较 curCoupon & best
            // todo 校验消费金额
            // todo 校验门槛等级
            // todo 校验城市范围
            // todo 校验过期时间......


        }


    }

    @Override
    protected void processCommandFinally(BestRecommendCondition<CouponFilterBO> condition, BestRecommendContent<List<CouponBO>> content) {

    }
}
