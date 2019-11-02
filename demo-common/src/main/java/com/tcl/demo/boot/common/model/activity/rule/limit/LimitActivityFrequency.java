package com.tcl.demo.boot.common.model.activity.rule.limit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LimitActivityFrequency implements Serializable {

    private Integer type;

    private Long frequency;

}
