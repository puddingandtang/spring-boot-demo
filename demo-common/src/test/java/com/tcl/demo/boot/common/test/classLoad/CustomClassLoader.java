package com.tcl.demo.boot.common.test.classLoad;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class CustomClassLoader extends ClassLoader {

    private String swapPath = "/Users/mr.tang/Documents/work/tangWork/testFolder/";

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] b = getClassByte(name);

        Class<?> clazz = defineClass(name, b, 0, b.length);

        if (null == clazz) {
            throw new ClassNotFoundException(name);
        }

        return clazz;
    }

    private byte[] getClassByte(String name) {

        String className = name.substring(name.lastIndexOf('.') + 1, name.length()) + ".class";

        try {

            FileInputStream fileInputStream = new FileInputStream(swapPath + className);

            byte[] buffer = new byte[1024];

            int length = 0;
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while ((length = fileInputStream.read(buffer)) > 0) {
                byteArrayOutputStream.write(buffer, 0, length);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }


    @Test
    public void CustomClassLoaderTest() {
        try {


            CustomClassLoader customClassLoader = new CustomClassLoader();

            Class clzz = customClassLoader.loadClass("People");

            // CustomClassLoader
            Object o = clzz.newInstance();

            // AppClassLoader
            // People p = new People();

            System.out.println(o.getClass().getClassLoader());

            System.out.println(o.getClass().getMethod("buildName").invoke(o));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
