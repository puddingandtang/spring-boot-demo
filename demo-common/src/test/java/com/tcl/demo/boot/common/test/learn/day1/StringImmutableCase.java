package com.tcl.demo.boot.common.test.learn.day1;

import org.junit.Test;

public class StringImmutableCase {


    @Test
    public void case1(){

        String a = "123";
        System.out.println(a.hashCode());

        // 这个 a 不再是 上面的 a
        a = a + "1";

        System.out.println(a.hashCode());

    }
}
