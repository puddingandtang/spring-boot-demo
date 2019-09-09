package com.tcl.demo.boot.service.common.processor;

import com.tcl.demo.boot.service.common.processor.BizProcessCondition;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class RuleVerifyCondition extends BizProcessCondition implements Serializable {

}
