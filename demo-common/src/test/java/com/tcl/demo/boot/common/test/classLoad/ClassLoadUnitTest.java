package com.tcl.demo.boot.common.test.classLoad;

import com.google.common.base.Splitter;
import org.junit.Test;

public class ClassLoadUnitTest {

    @Test
    public void acquireParentClassLoad() {

        // AppClassLoader
        // System.out.println(ClassLoadUnitTest.class.getClassLoader());

        // 获取bootstrap classLoader
        // System.out.println(System.getProperty("sun.boot.class.path"));

        // AppClassLoader
        // System.out.println(ClassLoader.getSystemClassLoader());

        ClassLoader curCLassLoad = Thread.currentThread().getContextClassLoader();

        while (null != curCLassLoad) {


            // cur :sun.misc.Launcher$AppClassLoader@18b4aac2
            // cur parent :sun.misc.Launcher$ExtClassLoader@37f8bb67
            // cur :sun.misc.Launcher$ExtClassLoader@37f8bb67
            // cur parent :null  这个就是bootstrap classLoader

            System.out.println("cur :" + curCLassLoad);

            ClassLoader parent = curCLassLoad.getParent();
            System.out.println("cur parent :" + parent);
            curCLassLoad = parent;
        }

    }
}
