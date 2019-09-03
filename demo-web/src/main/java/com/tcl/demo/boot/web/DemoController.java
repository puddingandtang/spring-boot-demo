package com.tcl.demo.boot.web;


import com.tcl.demo.boot.common.aop.ResponseAop;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.result.ResponseDTO;
import com.tcl.demo.boot.web.dto.DemoRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Slf4j
@Controller
@RequestMapping("demo")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class DemoController {

    @ResponseAop
    @RequestMapping(value = "checkHealthy", method = RequestMethod.GET)
    public ResponseDTO<Boolean> checkHealthy() {

        return new ResponseDTO<Boolean>().buildSuccess(Boolean.TRUE);
    }

    @ResponseAop
    @GetMapping(value = "checkResponseAop")
    public ResponseDTO<Boolean> checkResponseAop(DemoRequestDTO requestDTO) {

        // 人为抛出异常
        if (null == requestDTO) {
            throw new BizNormalException(ErrorCodes.PARAMETER_COMMON_ILLEGALITY);
        }

        // 代码抛出异常
        if (null == requestDTO.getUserNo()) {

            return new ResponseDTO<Boolean>().buildSuccess(Integer.MAX_VALUE / 0 == 0);
        } else {

            return new ResponseDTO<Boolean>().buildSuccess(true);
        }

    }


    @ResponseAop
    @GetMapping(value = "checkResponseAop_classType")
    public Boolean checkResponseAop_classType() {

        throw new RuntimeException("使用@ResponseAop，但是构建的返回结构不为ResponseDTO");
    }

}
