package com.tcl.demo.boot.web.user;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.tcl.demo.boot.common.aop.ResponseAop;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import com.tcl.demo.boot.common.result.ResponseDTO;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.dal.user.type.McAccountTypeEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import com.tcl.demo.boot.service.user.McAccountService;
import com.tcl.demo.boot.service.user.McIdentityService;
import com.tcl.demo.boot.service.user.bo.McAccountBO;
import com.tcl.demo.boot.service.user.bo.McIdentityBO;
import com.tcl.demo.boot.web.dto.BaseDTO;
import com.tcl.demo.boot.web.dto.IdentityResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("user")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class McUserController {

    @Resource
    McIdentityService mcIdentityService;

    @Resource
    McAccountService mcAccountService;

    @ResponseAop
    @RequestMapping(value = "queryIdentity", method = RequestMethod.GET)
    public ResponseDTO<IdentityResponseDTO> queryIdentity(String userNo, Integer userType) {

        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(userNo), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        PreconditionsAssert.assertTrue(null != userType, ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);

        McIdentityBO query = McIdentityBO.builder().userNo(userNo).userType(userType).build();
        McIdentityBO identityBO = mcIdentityService.queryIdentityInfoOrCreate(query);

        PreconditionsAssert.assertNotNull(identityBO, ErrorCodes.IDENTITY_NOT_EXIST);

        IdentityResponseDTO result = IdentityResponseDTO.builder()
                .identityNo(identityBO.getIdentityNo())
                .userNo(identityBO.getUserNo())
                .userType(identityBO.getUserType())
                .build();

        return new ResponseDTO<IdentityResponseDTO>().buildSuccess(result);

    }

    @ResponseAop
    @RequestMapping(value = "queryAccount", method = RequestMethod.GET)
    public ResponseDTO<String> queryAccount(String userNo, Integer userType) {

        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(userNo), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        PreconditionsAssert.assertNotNull(McUserTypeEnum.acquireByType(userType), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);

        McIdentityBO query = McIdentityBO.builder().userNo(userNo).userType(userType).build();
        McIdentityBO identityBO = mcIdentityService.queryIdentityInfoOrCreate(query);
        PreconditionsAssert.assertNotNull(identityBO, ErrorCodes.IDENTITY_NOT_EXIST);

        List<McAccountBO> mcAccountBOs = mcAccountService.queryAccountInfoOrDefaultCreate(identityBO.getIdentityNo(), identityBO.getUserType());
        PreconditionsAssert.assertTrue(!CollectionTool.isEmpty(mcAccountBOs), ErrorCodes.IDENTITY_NOT_EXIST);

        return new ResponseDTO<String>().buildSuccess(JSON.toJSONString(mcAccountBOs));

    }


    @ResponseAop
    @RequestMapping(value = "queryAccountForAccount", method = RequestMethod.GET)
    public ResponseDTO<String> queryAccountForAccount(String userNo, Integer userType, Integer accountType) {

        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(userNo), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        PreconditionsAssert.assertNotNull(McUserTypeEnum.acquireByType(userType), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        PreconditionsAssert.assertNotNull(McAccountTypeEnum.acquireByTypeCode(accountType), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);


        McIdentityBO query = McIdentityBO.builder().userNo(userNo).userType(userType).build();
        McIdentityBO identityBO = mcIdentityService.queryIdentityInfoOrCreate(query);
        PreconditionsAssert.assertNotNull(identityBO, ErrorCodes.IDENTITY_NOT_EXIST);

        McAccountBO mcAccountBO = mcAccountService.queryAccountInfoOrCreate(identityBO.getIdentityNo(), accountType);
        PreconditionsAssert.assertNotNull(mcAccountBO, ErrorCodes.IDENTITY_NOT_EXIST);

        return new ResponseDTO<String>().buildSuccess(JSON.toJSONString(mcAccountBO));

    }


}
