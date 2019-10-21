package com.tcl.demo.boot.service.base.script;

import com.tcl.demo.boot.service.base.script.bo.FlowBO;
import com.tcl.demo.boot.service.base.script.bo.FlowScriptContainer;
import com.tcl.demo.boot.service.base.script.bo.ScriptBO;

import java.util.List;

public interface ScriptService {

    /**
     * 获取脚本集合
     *
     * @param flowNo
     * @return
     */
    List<ScriptBO> acquireScript(Long flowNo);

    /**
     * 获取流程
     *
     * @param flowNo
     * @return
     */
    FlowBO acquireFlow(Long flowNo);

    /**
     * 获取流程容器
     *
     * @param flowNo
     * @return
     */
    FlowScriptContainer acquireFLowScriptContainer(Long flowNo);
}
