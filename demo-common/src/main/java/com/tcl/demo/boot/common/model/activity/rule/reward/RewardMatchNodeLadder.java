package com.tcl.demo.boot.common.model.activity.rule.reward;

import lombok.Data;


import java.io.Serializable;
import java.util.List;

@Data
public class RewardMatchNodeLadder implements Serializable {

    private static final long serialVersionUID = 610459148073661787L;

    public static int LADDER_TYPE_NUMBER = 1;

    public static int LADDER_TYPE_RANGE = 2;

    private Integer ladderType;

    /**
     * ladderType == LADDER_TYPE_NUMBER ,使用这个值
     */
    private Long ladderBegin;

    /**
     * ladderType == LADDER_TYPE_NUMBER ,使用这个值
     */
    private Long ladderEnd;

    /**
     * ladderType == LADDER_TYPE_RANGE ,使用这个值
     */
    private List<String> ladderRange;


}
