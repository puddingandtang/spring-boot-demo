package com.tcl.demo.boot.service.test.leetCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OneWayNode {

    /**
     * 当前值
     */
    private Long value;

    /**
     * 后继节点
     */
    private OneWayNode next;

}
