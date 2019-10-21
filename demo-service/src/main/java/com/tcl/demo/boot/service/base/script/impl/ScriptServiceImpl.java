package com.tcl.demo.boot.service.base.script.impl;

import com.tcl.demo.boot.service.base.script.ScriptService;
import com.tcl.demo.boot.service.base.script.bo.FlowBO;
import com.tcl.demo.boot.service.base.script.bo.FlowScriptContainer;
import com.tcl.demo.boot.service.base.script.bo.ScriptBO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ScriptServiceImpl implements ScriptService {

    @Override
    public List<ScriptBO> acquireScript(Long flowNo) {

        List<ScriptBO> scripts = Lists.newArrayList();

        // todo 通过flowNo 查询mc_flow_script_relation 获取scriptNo

        // todo 通过scriptNo 查询mc_script_info 获取脚本信息

        return scripts;
    }

    @Override
    public FlowBO acquireFlow(Long flowNo) {

        // todo 通过flowNo 查询mc_flow_info

        return null;
    }

    @Override
    public FlowScriptContainer acquireFLowScriptContainer(Long flowNo) {

        FlowBO flow = acquireFlow(flowNo);

        List<ScriptBO> scripts = acquireScript(flowNo);

        return null;
    }

}
