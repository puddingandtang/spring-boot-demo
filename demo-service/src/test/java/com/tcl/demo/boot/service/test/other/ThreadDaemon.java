package com.tcl.demo.boot.service.test.other;

public class ThreadDaemon {

    public static class ThreadA implements Runnable {

        @Override
        public void run() {

            while (true) {

                System.out.println("print:" + System.currentTimeMillis());

                try {

                    Thread.sleep(1000);

                } catch (Exception e) {

                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {

        ThreadA threadA = new ThreadA();

        Thread thread = new Thread(threadA);
        // 设置守护线程，thread
        // 用户线程，main
        /**
         * 因此main线程结束后，守护线程自动结束
         */
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(2000);
        } catch (Exception e) {

        }


    }
}