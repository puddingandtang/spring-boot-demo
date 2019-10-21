package com.tcl.demo.boot.service.base.script.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlowScriptContainer implements Serializable {

    /**
     * 流程
     */
    private FlowBO flow;

    /**
     * 脚本列表
     */
    private List<ScriptBO> scripts;

}
