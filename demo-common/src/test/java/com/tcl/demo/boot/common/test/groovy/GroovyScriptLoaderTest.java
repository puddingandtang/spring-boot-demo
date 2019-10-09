package com.tcl.demo.boot.common.test.groovy;

import com.google.common.collect.Maps;
import com.tcl.demo.boot.common.groovy.GroovyObjectHolder;
import com.tcl.demo.boot.common.groovy.GroovyScriptLoader;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class GroovyScriptLoaderTest extends GroovyScriptLoader {

    @Override
    protected GroovyObjectHolder load(Long scriptNo) {

        if (null == scriptNo) {

            return GroovyObjectHolder.builder().scriptNo(GroovyObjectHolder.NOT_EXIST).build();
        }

        String scriptTxt = "package com.tcl.demo.boot.common.test.groovy;\n" +
                "\n" +
                "import java.text.MessageFormat;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "public class GroovyScriptTxt {\n" +
                "\n" +
                "    public String test(Map<String, Object> params) {\n" +
                "\n" +
                "        return MessageFormat.format(\"GroovyScriptTxt-test-入参:[{0}]\", params);\n" +
                "\n" +
                "    }\n" +
                "}\n";

        String scriptMd5 = scriptTxt.hashCode() + "";


        return GroovyObjectHolder.builder().scriptNo(scriptNo).scriptMD5(scriptMd5).scriptTxt(scriptTxt).build();
    }


    @Test
    public void invoke() {

        GroovyScriptLoaderTest loaderTest = new GroovyScriptLoaderTest();

        for (long scriptNo = 1L; scriptNo < 5L; scriptNo++) {

            GroovyObjectHolder holder = loaderTest.acquireGroovyObjectForScriptNo(scriptNo);

            if (!holder.allCompliance()) {
                continue;
            }

            Map<String, Long> params = Maps.newHashMap();
            params.put("test", scriptNo);

            String rst = (String) holder.getGroovyObject().invokeMethod("test", params);

            System.out.println(rst);

            System.out.println(loaderTest.getClassCache());
            loaderTest.clearALlCache();
            // System.out.println(loaderTest.getGroovyObjectHolderCache());

        }


    }
}
