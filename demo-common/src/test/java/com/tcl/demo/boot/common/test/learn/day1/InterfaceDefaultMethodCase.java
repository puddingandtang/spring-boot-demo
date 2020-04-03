package com.tcl.demo.boot.common.test.learn.day1;

public interface InterfaceDefaultMethodCase {

    final String a1 = "final 修饰";

    static String a2 = "static 修饰";

    default String defaultMethod(String name) {

        return "java8开始接口存在一个默认的方法实现：" + name;
    }

    default String defaultMethod(String name, String version) {

        return "java8开始接口存在一个默认的方法实现：" + name + "_" + version;
    }


    default String defaultMethod1(String name, String version) {

        return "java8开始接口存在一个默认的方法实现：" + name + "_" + version;
    }

}

class AInterfaceImplements implements InterfaceDefaultMethodCase {

}

class DefaultMain {

    Integer classParam;

    public static void main(String[] args) {

        Integer methodParam;

        DefaultMain defaultMain = new DefaultMain();
        System.out.println(defaultMain.classParam);


        InterfaceDefaultMethodCase interfaceDefaultMethodCase = new AInterfaceImplements();

        System.out.println(InterfaceDefaultMethodCase.a1);

        System.out.println(InterfaceDefaultMethodCase.a2);

        String result = interfaceDefaultMethodCase.defaultMethod("A");
        System.out.println(result);

        result = interfaceDefaultMethodCase.defaultMethod("A", "1.0");
        System.out.println(result);

        result = interfaceDefaultMethodCase.defaultMethod1("B", "1.0");
        System.out.println(result);
    }


}