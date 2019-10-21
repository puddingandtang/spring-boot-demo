package com.tcl.demo.boot.service.base.script.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowBO implements Serializable {

    private Long flowNo;

    private Integer bizClassOne;

    private Integer bizClassTwo;

    private String bizDesc;

    private Integer flowStatus;

}
