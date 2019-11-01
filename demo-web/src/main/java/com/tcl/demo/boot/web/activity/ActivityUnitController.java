package com.tcl.demo.boot.web.activity;

import com.tcl.demo.boot.common.aop.ResponseAop;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import com.tcl.demo.boot.common.result.ResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Slf4j
@Controller
@RequestMapping("activityUnit")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ActivityUnitController {

//    @ResponseAop
//    @RequestMapping(value = "acquireUnit", method = RequestMethod.GET)
//    public ResponseDTO<Map<String, String>> acquireUnit(Integer lv) {
//
//        PreconditionsAssert.assertNotNull(null != lv, ErrorCodes.);
//
//        return null;
//    }

}
