package com.tcl.demo.boot.common.test.learn;

import org.junit.Test;

public class TryCatchCase {


    @Test
    public void error() {


        /** 这种场景finally会执行*/
        errorInTryCase1();

        /** 这种场景finally会抛异常*/
        errorInCatchCase1();

    }

    public void errorInTryCase1() {

        try {

            int a = 9 / 0;

            System.out.println("case 1- errorInTry - out " + a);

        } finally {

            System.out.println("case 1- errorInTry- finally");
        }

    }

    public void errorInCatchCase1() {

        try {


            int a = 9;

            System.out.println("case 2- errorInTry - out " + a);

        } finally {

            System.out.println("case 2- errorInTry- finally - error -before");

            int a = 9 / 0;

            System.out.println("case 2- errorInTry- finally");
        }
    }

    @Test
    public void returnCatch() {

        /** finally会覆盖try里面的return*/
        System.out.println(returnCatchCase1());

        System.out.println(returnCatchCase2());
    }


    public int returnCatchCase1() {

        try {

            return 1;

        } finally {

            return 0;

        }
    }

    public int returnCatchCase2() {

        try {

            int a = 1/0;
            return 1;
        }
        catch (Exception e){

            return 2;

        }finally {

            return 0;

        }
    }
}
