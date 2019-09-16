package com.tcl.demo.boot.service.common.command;

import com.tcl.demo.boot.service.common.processor.BizProcessContent;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode
public class BestRecommendContent<T> extends BizProcessContent implements Serializable {

    private static final long serialVersionUID = 5141630468103542073L;

    /**
     * 操作数据
     */
    private T commandData;

    /**
     * 结果数据
     */
    private T resultData;
}
