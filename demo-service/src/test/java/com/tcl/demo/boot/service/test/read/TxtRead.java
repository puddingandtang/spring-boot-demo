package com.tcl.demo.boot.service.test.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class TxtRead {

    public static void main(String[] args) {

        System.out.println(System.currentTimeMillis());
        // System.out.println(Long.valueOf(Math.pow(2,53)+""));
        // File file = new File("/Users/mr.tang/Desktop/检查数据.txt");
         // File file = new File("/Users/mr.tang/Desktop/exchange_no 4.txt");
        // System.out.println(txt2String(file));

    }

    // CONCAT(user_no,',', biz_trade_no)
    public static String txt2String(File file) {

        int idx = 0;
        StringBuilder result = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;

            while ((s = br.readLine()) != null) {//使用readLine方法，一次读一行

                String temp = "\'" + s + "\',";
                result.append(temp);
                idx++;

            }

            System.out.println("行数：" + idx);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }



}
