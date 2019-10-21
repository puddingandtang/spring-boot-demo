package com.tcl.demo.boot.service.activity.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 活动链路上下文
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityTraceContext implements Serializable {

    /**
     * 活动上下文
     */
    private ActivityContext activity;

    /**
     * 活动参与上下文
     */
    private ActivityParticipantContext participant;

    /**
     * 中断标识
     */
    private boolean interrupt = false;


}
