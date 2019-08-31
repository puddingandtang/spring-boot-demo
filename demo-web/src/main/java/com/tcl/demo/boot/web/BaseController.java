package com.tcl.demo.boot.web;


import com.tcl.demo.boot.common.result.ResponseDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("base")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class BaseController {

    @GetMapping(value = "checkHealthy")
    public ResponseDTO<Boolean> checkHealthy() {

        return new ResponseDTO<Boolean>().buildSuccess(Boolean.TRUE);
    }
}
