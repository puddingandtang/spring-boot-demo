package com.tcl.demo.boot.service.user.impl;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.common.tool.SnowflakeIdTool;
import com.tcl.demo.boot.dal.user.dataobject.McAccountInfoDataObject;
import com.tcl.demo.boot.dal.user.mapper.McAccountInfoMapper;
import com.tcl.demo.boot.dal.user.type.McAccountStatusEnum;
import com.tcl.demo.boot.dal.user.type.McAccountTypeEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import com.tcl.demo.boot.service.user.McAccountService;
import com.tcl.demo.boot.service.user.bo.McAccountBO;
import com.tcl.demo.boot.service.user.bo.McAccountTransform;
import com.tcl.demo.boot.service.user.component.AccountInitConfigComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class McAccountServiceImpl implements McAccountService {

    @Resource
    McAccountInfoMapper mcAccountInfoMapper;

    @Resource
    AccountInitConfigComponent accountInitConfigComponent;

    @Override
    public List<McAccountBO> queryAccountInfoOrDefaultCreate(String identityNo, Integer userType) {

        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(identityNo), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        PreconditionsAssert.assertNotNull(McUserTypeEnum.acquireByType(userType), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);

        List<McAccountInfoDataObject> mcAccountInfos = mcAccountInfoMapper.queryAccountInfoByIdentityNo(identityNo);

        // 查询到就直接返回
        if (!CollectionTool.isEmpty(mcAccountInfos)) {
            return McAccountTransform.do2bo(mcAccountInfos);
        }

        // 初始化账户配置
        this.initAccountInfo(identityNo, null, userType);

        mcAccountInfos = mcAccountInfoMapper.queryAccountInfoByIdentityNo(identityNo);
        return McAccountTransform.do2bo(mcAccountInfos);
    }

    @Override
    public McAccountBO queryAccountInfoOrCreate(String identityNo, Integer accountType) {

        PreconditionsAssert.assertTrue(!Strings.isNullOrEmpty(identityNo), ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);
        McAccountTypeEnum accountTypeEnum = McAccountTypeEnum.acquireByTypeCode(accountType);
        PreconditionsAssert.assertNotNull(accountTypeEnum, ErrorCodes.QUERY_ACCOUNT_PARAM_ILLEGALITY);

        McAccountInfoDataObject mcAccountInfo = mcAccountInfoMapper.queryAccountInfoByIdentityNoOne(identityNo, accountType);
        if (null != mcAccountInfo) {
            return McAccountTransform.do2bo(mcAccountInfo);
        }

        // 初始化账户信息
        this.initAccountInfo(identityNo, Lists.newArrayList(accountTypeEnum), null);

        mcAccountInfo = mcAccountInfoMapper.queryAccountInfoByIdentityNoOne(identityNo, accountType);

        return McAccountTransform.do2bo(mcAccountInfo);
    }


    /**
     * 初始化
     *
     * @param identityNo
     * @param types
     * @param userType
     */
    private void initAccountInfo(String identityNo, List<McAccountTypeEnum> types, Integer userType) {

        if (CollectionTool.isEmpty(types) && null != McUserTypeEnum.acquireByType(userType)) {
            // 初始化配置 => types为空，userType存在，则加载初始化配置
            types = accountInitConfigComponent.acquireValidInitAccountType(userType);
        }

        if (CollectionTool.isEmpty(types)) {
            log.error("初始化账户类型配置非法，初始化账户失败");
            return;
        }

        for (McAccountTypeEnum curType : types) {

            try {

                McAccountInfoDataObject create = new McAccountInfoDataObject();
                create.setAccountNo(String.valueOf(SnowflakeIdTool.nextId()));
                create.setAccountType(curType.getTypeCode());
                create.setIdentityNo(identityNo);
                create.setStatus(McAccountStatusEnum.USABLE.getStatusCode());
                mcAccountInfoMapper.createAccountInfos(Lists.newArrayList(create));

            } catch (Throwable e) {

                if (!(e instanceof DuplicateKeyException)) {
                    log.error("用户身份[" + identityNo + "],账户类型[" + curType.getTypeDesc() + "]创建失败，失败原因：", e);
                }
            }
        }
    }
}
