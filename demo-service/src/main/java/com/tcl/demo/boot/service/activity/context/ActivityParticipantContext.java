package com.tcl.demo.boot.service.activity.context;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动参与人上下文
 */
@Data
public class ActivityParticipantContext implements Serializable {

    /**
     * 账户编号
     */
    private Long accountNo;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 业务自定义时间
     */
    private Long time;

    /**
     * 系统请求时间
     */
    private Long requestTime;

    /**
     * 终端
     */
    private Integer terminal;

    /**
     * 活动编号
     * 一些场景，上层业务直接传入活动编号
     */
    private Long activityNo;

    /**
     * 活动场景
     */
    private Integer scene;
}