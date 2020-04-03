package com.tcl.demo.boot.common.test.learn.day1;

import org.junit.Test;

public class NoArgsConstructorCase {

    @Test
    public void superClassConstruct() {

        //Java 程序在执行子类的构造方法之前，如果没有用 super()来调用父类特定的构造方法，则会调用父类中“没有参数的构造方法”。
        //因此，如果父类中只定义了有参数的构造方法，而在子类的构造方法中又没有用 super()来调用父类中特定的构造方法，则编译时将发生错误，
        //因为 Java 程序在父类中找不到没有参数的构造方法可供执行。解决办法是在父类里加上一个不做事且没有参数的构造方法。 　

        Son son = new Son("son");
    }


}


class Father {

    String name;

    public Father() {

        System.out.println("无参构造Father");

    }

    public Father(String name) {

        this.name = name;
    }

}

class Son extends Father {

    String name;

    public Son(String name) {

        // 这里会隐式调用super();
        // 如果Father没有无参构造方法，这会出现编译错误
        this.name = name;
    }
}
