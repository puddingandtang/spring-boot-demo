package com.tcl.demo.boot.service.activity.context;

import com.tcl.demo.boot.service.activity.type.ActivityResultCodeEnum;
import lombok.Data;

import java.io.Serializable;

@Data
public class ActivityResponse implements Serializable {

    /**
     * 活动结果码
     * <p>
     * 默认值：{@link ActivityResultCodeEnum#SUC}
     * {@link ActivityResultCodeEnum#getCode()}
     */
    private Integer resultCode = ActivityResultCodeEnum.SUC.getCode();


}
