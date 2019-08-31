package com.tcl.demo.boot.common.test;

import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.result.ResponseDTO;
import org.junit.Assert;
import org.junit.Test;

public class ResponseDTOTest {


    @Test
    public void build_success_case() {


        ResponseDTO<Integer> responseDTO = new ResponseDTO<Integer>().buildSuccess(1);

        Assert.assertNotNull(responseDTO);
        Assert.assertTrue("结构体success不为true", responseDTO.getCode() == ErrorCode.SUCCESS_CODE && responseDTO.isSuccess());
    }

    @Test
    public void build_fail_case() {

        try {

            new ResponseDTO<Integer>().buildFail(200, "构建错误场景，单错误码确是成功");

        } catch (AssertionError e) {

            Assert.assertTrue(e.getMessage().equals("错误码设置非法"));
        }

        ResponseDTO<Integer> responseDTO = new ResponseDTO<Integer>().buildFail(400, "400错误");

        Assert.assertNotNull(responseDTO);
        Assert.assertTrue(responseDTO.getCode() != ErrorCode.SUCCESS_CODE && !responseDTO.isSuccess());
    }
}
