package com.tcl.demo.boot.common.test.learn.day3;

import org.junit.Test;

import java.io.*;

public class IOStreamCase {

    @Test
    public void blockingInput_byte() {

        InputStream inputStream = null;

        try {

            File file = new File("/Users/mr.tang/Desktop/io.txt");

            if (!file.exists()) {
                System.err.println("文件不存在");
                return;
            }

            // 字节流
            // 1字节8位，1b = 1024kb
            inputStream = new FileInputStream(file);

//            int curByteValue = inputStream.read();
//            while (curByteValue != -1) {
//
//                System.out.print(curByteValue + "_");
//                curByteValue = inputStream.read();
//
//            }

            byte[] all = new byte[inputStream.available()];
            int x = inputStream.read(all);

            String str = new String(all, "utf-8");

            System.out.println(str);

        } catch (Throwable throwable) {

            throwable.printStackTrace();

        } finally {

            try {

                if (null != inputStream) {
                    inputStream.close();
                }

            } catch (Exception e) {

            }

        }


    }

    @Test
    public void blockingOutput_byte() {

        OutputStream outputStream = null;

        try {

            File file = new File("/Users/mr.tang/Desktop/io.txt");

            outputStream = new FileOutputStream(file, true);

            String value = System.currentTimeMillis() + ":一行\n";

            outputStream.write(value.getBytes("utf-8"));

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (null != outputStream) {

                    outputStream.close();
                }
            } catch (Exception e) {
            }
        }


    }

    @Test
    public void blockingInput_char() {

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {

            File file = new File("/Users/mr.tang/Desktop/io.txt");

            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);

            // 将字节流转换为字符流，和上述的FileReader获取结果一样
            // InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
            // bufferedReader = new BufferedReader(inputStreamReader);

            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {

        } finally {
            try {

                if (null != bufferedReader) {

                    bufferedReader.close();
                }

                if (null != bufferedReader) {

                    bufferedReader.close();
                }

            } catch (Exception e) {
            }


        }


    }


    @Test
    public void arithmetic() {

        int a = 10 >> 1; //向右移动一位 1010 >> 1
        System.out.println("a=" + a); // 5
        int b = a++; //先用后加，
        System.out.println("a=" + a + "；b=" + b); // b = 5 ， a = 6
        int c = ++a; // 先加后用
        System.out.println("a=" + a + "；c=" + c); // c = 7 ， a = 7
        int d = b * a++; // 先用后加
        System.out.println(a); // 8
        System.out.println(b); // 5
        System.out.println(c); // 7
        System.out.println(d); // 35



    }

}
