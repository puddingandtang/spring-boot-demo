package com.tcl.demo.boot.common.model.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotifyConfig implements Serializable {

    /**
     * 是否开启通知配置
     */
    private Boolean open;

    /**
     * push
     */
    private PushElement push;
}
