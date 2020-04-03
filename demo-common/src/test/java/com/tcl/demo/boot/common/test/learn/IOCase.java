package com.tcl.demo.boot.common.test.learn;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;

public class IOCase {

    @Test
    public void fileInput() {

        try (InputStream inputStream = new FileInputStream("");) {

            inputStream.read();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @Test
    public void fileOutput(){

        // Files.write()


    }
}
