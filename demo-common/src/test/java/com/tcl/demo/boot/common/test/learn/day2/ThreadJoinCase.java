package com.tcl.demo.boot.common.test.learn.day2;

public class ThreadJoinCase {

    public static void main(String[] args) {

        Thread curThread = Thread.currentThread();

        System.out.println(curThread.getName()+"开始执行");

        Thread otherThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try{

                    for(int times =0 ; times < 5 ;times++){

                        Thread.sleep(10_000);
                        System.out.println("第"+(times +1)+"次睡眠，睡眠10_0000ms");
                    }

                }catch (Exception e){

                    e.printStackTrace();
                }

            }
        });

        otherThread.start();

        try{

            // 等到超时了，otherThread线程依然执行，当前线程Main就不进行等待，继续往下执行
            // otherThread.join(25_000);

            System.out.println(otherThread.isAlive());

            otherThread.join();

            // otherThread执行完成后isAlive返回false，即该线程是结束。
            System.out.println(otherThread.isAlive());

            Thread.sleep(1_000);

            System.out.println(otherThread.isAlive());

        }catch (Exception e){

            e.printStackTrace();
        }

        System.out.println(curThread.getName()+"执行结束");



    }




}


