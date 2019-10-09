package com.tcl.demo.boot.common.test.groovy;

import java.text.MessageFormat;
import java.util.Map;

public class GroovyScriptTxt {

    public String test(Map<String, Object> params) {

        return MessageFormat.format("GroovyScriptTxt-test-入参:[{0}]", params);

    }
}
