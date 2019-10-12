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

    private ActivityContext activity;

    private ActivityParticipantContext participant;


}
