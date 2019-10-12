package com.tcl.demo.boot.service.activity.context;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 活动信息上下文
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityContext implements Serializable {

    private Long activityId;
}
