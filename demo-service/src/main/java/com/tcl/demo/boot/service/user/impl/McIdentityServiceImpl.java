package com.tcl.demo.boot.service.user.impl;

import com.google.common.base.Strings;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import com.tcl.demo.boot.common.tool.SnowflakeIdTool;
import com.tcl.demo.boot.dal.user.dataobject.McIdentityInfoDataObject;
import com.tcl.demo.boot.dal.user.mapper.McIdentityInfoMapper;
import com.tcl.demo.boot.dal.user.type.McIdentityStatusEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import com.tcl.demo.boot.service.user.McIdentityService;
import com.tcl.demo.boot.service.user.bo.McIdentityBO;
import com.tcl.demo.boot.service.user.bo.McIdentityTransform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class McIdentityServiceImpl implements McIdentityService {

    @Resource
    McIdentityInfoMapper mcIdentityInfoMapper;

    @Override
    public McIdentityBO queryIdentityInfoOrCreate(McIdentityBO queryBO) {

        PreconditionsAssert.assertNotNull(queryBO, ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);

        String userNo = queryBO.getUserNo();
        McUserTypeEnum userType = McUserTypeEnum.acquireByType(queryBO.getUserType());
        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(userNo), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        PreconditionsAssert.assertNotNull(userType, ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);

        McIdentityInfoDataObject dbIdentity = mcIdentityInfoMapper.queryIdentityInfo(userNo, userType.getType());
        if (null != dbIdentity) {
            return McIdentityTransform.do2bo(dbIdentity);
        }

        try {
            // 构建identity - 可以使用雪花算法
            queryBO.setIdentityNo(String.valueOf(SnowflakeIdTool.nextId()));
            queryBO.setStatus(McIdentityStatusEnum.USABLE.getStatusCode());
            McIdentityInfoDataObject dbInsert = McIdentityTransform.bo2do(queryBO);
            // 不关注构建结果，除非构建异常
            mcIdentityInfoMapper.createIdentityInfo(dbInsert);

        } catch (Throwable e) {
            if (!(e instanceof DuplicateKeyException)) {
                log.error("初始化营销身份信息异常：", e);
            }
        }

        return McIdentityTransform.do2bo(mcIdentityInfoMapper.queryIdentityInfo(userNo, userType.getType()));
    }
}
