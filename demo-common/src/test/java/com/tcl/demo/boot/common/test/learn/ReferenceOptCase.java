package com.tcl.demo.boot.common.test.learn;

import org.junit.Test;

public class ReferenceOptCase {

    @Test
    public void swapObject() {

        AClass a1 = AClass.builder().seq(1L).build();

        AClass a2 = AClass.builder().seq(2L).build();

        swap(a1, a2);

        System.out.println("a1 :" + a1);
        System.out.println("a2 :" + a2);
    }

    public void swap(AClass a1, AClass a2) {

        AClass temp = a2;
        a2 = a1;
        a1 = temp;

        System.out.println("a1 :" + a1);
        System.out.println("a2 :" + a2);

    }

    @Test
    public void case1() {

        AClass a1 = AClass.builder().seq(1L).build();

        AClass a2 = null;

        errorAssignment(a1, a2);

        System.out.println("a1 :" + a1);
        System.out.println("a2 :" + a2);
    }

    public void errorAssignment(AClass in, AClass out) {


        out = in;

        System.out.println("out:" + out);

    }

    @Test
    public void case2() {

        AClass a1 = AClass.builder().seq(1L).build();

        AClass a2 = a1;
        a2.setSeq(9L);

        System.out.println("a1 :" + a1);
        System.out.println("a2 :" + a2);

    }

}
