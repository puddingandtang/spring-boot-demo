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
public class ScriptBO implements Serializable {

    private Long scriptNo;

    private String scriptMd5;

    private Integer scriptType;

    private String scriptContext;

    private Integer scriptStatus;

}
