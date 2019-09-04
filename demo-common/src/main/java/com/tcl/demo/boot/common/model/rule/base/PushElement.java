package com.tcl.demo.boot.common.model.rule.base;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
public class PushElement implements Serializable {

    private static final long serialVersionUID = 8656379208622922934L;

    /**
     * push编号-营销消息模版编号
     * <p>
     * 如果pushNo不合法，则取pushTitle，pushContent
     */
    private Long pushNo;

    /**
     * push标题
     */
    private String pushTitle;

    /**
     * push内容
     */
    private String pushContent;
}
