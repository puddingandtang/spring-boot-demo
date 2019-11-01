package com.tcl.demo.boot.common.model.activity.rule.reward;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum RewardMatchNode {

    COMMON(0, "通用节点", false),

    SUCCESS_ORDER(1, "成单规则节点", true);


    RewardMatchNode(Integer type, String desc, Boolean existLadder) {
        this.type = type;
        this.desc = desc;
        this.existLadder = existLadder;
    }

    private Integer type;

    private String desc;

    private Boolean existLadder;

    public static final RewardMatchNode acquireByType(final Integer type) {

        if (null == type) {

            return null;
        }

        return Arrays.stream(RewardMatchNode.values())
                .filter(t0 -> type.intValue() == t0.getType())
                .findFirst()
                .orElse(null);
    }
}
