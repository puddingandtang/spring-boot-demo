package com.tcl.demo.boot.service.common.command;

import com.tcl.demo.boot.service.common.processor.BizProcessCondition;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class BestRecommendCondition<T> extends BizProcessCondition implements Serializable {

    private static final long serialVersionUID = -6036376789090837841L;

    private T condition;

}
