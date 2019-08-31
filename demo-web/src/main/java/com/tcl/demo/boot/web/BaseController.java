package com.tcl.demo.boot.web;


import com.tcl.demo.boot.common.aop.ResponseAop;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.result.ResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("base")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class BaseController {

    // @ResponseAop
    @RequestMapping(value = "checkHealthy", method = RequestMethod.GET)
    public ResponseDTO<Boolean> checkHealthy() {

        return new ResponseDTO<Boolean>().buildSuccess(Boolean.TRUE);
    }


    @ResponseAop
    @GetMapping(value = "checkResponseAop")
    public ResponseDTO<Boolean> checkResponseAop(Integer data) {

        // 人为抛出异常
        if (null == data){
            throw new BizNormalException(ErrorCodes.PARAMETER_COMMON_ILLEGALITY);
        }

        Boolean result = Boolean.FALSE;

        // 代码抛出异常
        if(0 == data){

            result = Integer.MAX_VALUE / data == 0;
        }else {

            result = Boolean.TRUE;
        }

        return new ResponseDTO<Boolean>().buildSuccess(result);
    }
}
