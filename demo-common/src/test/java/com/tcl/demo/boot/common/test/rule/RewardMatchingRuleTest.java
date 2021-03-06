package com.tcl.demo.boot.common.test.rule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.model.rule.activity.reward.RewardContent;
import com.tcl.demo.boot.common.model.rule.activity.reward.RewardMatchNode;
import com.tcl.demo.boot.common.model.rule.activity.reward.RewardMatchNodeLadder;
import com.tcl.demo.boot.common.model.rule.activity.reward.RewardMatchingRule;
import com.tcl.demo.boot.common.model.rule.base.NotifyConfig;
import com.tcl.demo.boot.common.model.rule.base.PushElement;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

public class RewardMatchingRuleTest {

    @Test
    @Ignore
    public void buildRuleObject2JSONString() {

        RewardContent commonReward = new RewardContent();
        commonReward.setRewardNo(1L);

        NotifyConfig notifyConfigA = new NotifyConfig();
        notifyConfigA.setOpen(false);

        NotifyConfig notifyConfigB = new NotifyConfig();
        notifyConfigB.setOpen(true);
        PushElement pushElement = new PushElement();
        pushElement.setPushNo(1L);
        notifyConfigB.setPush(pushElement);

        RewardMatchingRule ruleA = new RewardMatchingRule();
        ruleA.setVersion("1.1");
        ruleA.setValid(false);
        ruleA.setNodeMatchType(RewardMatchNode.COMMON.getType());
        ruleA.setSort(1);
        ruleA.setLadder(null);
        ruleA.setRewardContents(Lists.newArrayList(commonReward));
        ruleA.setNotifyConfig(notifyConfigA);


        RewardMatchingRule ruleB = new RewardMatchingRule();
        ruleB.setVersion("1.2");
        ruleB.setNodeMatchType(RewardMatchNode.SUCCESS_ORDER.getType());
        ruleB.setSort(1);
        RewardMatchNodeLadder ladderB = new RewardMatchNodeLadder();
        ladderB.setLadderType(RewardMatchNodeLadder.LADDER_TYPE_NUMBER);
        ladderB.setLadderBegin(0L);
        ladderB.setLadderEnd(100L);
        ruleB.setLadder(ladderB);
        ruleB.setRewardContents(Lists.newArrayList(commonReward));
        ruleB.setNotifyConfig(notifyConfigA);

        RewardMatchingRule ruleC = new RewardMatchingRule();
        ruleC.setNodeMatchType(RewardMatchNode.SUCCESS_ORDER.getType());
        ruleC.setSort(2);
        RewardMatchNodeLadder ladderC = new RewardMatchNodeLadder();
        ladderC.setLadderType(RewardMatchNodeLadder.LADDER_TYPE_RANGE);
        ladderC.setLadderRange(Lists.newArrayList("A", "B"));
        ruleC.setLadder(ladderC);
        ruleC.setRewardContents(Lists.newArrayList(commonReward));
        ruleC.setNotifyConfig(notifyConfigB);

        List<RewardMatchingRule> rules = Lists.newArrayList(ruleA, ruleB, ruleC);

        // System.out.println(JSON.toJSONString(rules));

        String ruleJsonStr = "[{\"nodeMatchType\":0,\"notifyConfig\":{\"open\":false},\"rewardContents\":[{\"rewardNo\":1}],\"ruleCode\":\"ACTIVITY_REWARD_MATCHING_RULE\",\"sort\":1,\"valid\":false,\"version\":\"1.1\"},{\"ladder\":{\"ladderBegin\":0,\"ladderEnd\":100,\"ladderType\":1},\"nodeMatchType\":1,\"notifyConfig\":{\"$ref\":\"$[0].notifyConfig\"},\"rewardContents\":[{\"$ref\":\"$[0].rewardContents[0]\"}],\"ruleCode\":\"ACTIVITY_REWARD_MATCHING_RULE\",\"sort\":1,\"valid\":true,\"version\":\"1.2\"},{\"ladder\":{\"ladderRange\":[\"A\",\"B\"],\"ladderType\":2},\"nodeMatchType\":1,\"notifyConfig\":{\"open\":true,\"push\":{\"pushNo\":1}},\"rewardContents\":[{\"$ref\":\"$[0].rewardContents[0]\"}],\"ruleCode\":\"ACTIVITY_REWARD_MATCHING_RULE\",\"sort\":2,\"valid\":true,\"version\":\"1.0\"}]" ;
        List<RewardMatchingRule> rulesFrom =JSONArray.parseArray(ruleJsonStr, RewardMatchingRule.class);
        System.out.println(rulesFrom);

        System.out.println(JSON.toJSONString(rulesFrom));
    }
}
