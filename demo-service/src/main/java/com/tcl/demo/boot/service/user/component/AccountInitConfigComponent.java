package com.tcl.demo.boot.service.user.component;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.cache.AbstractCache;
import com.tcl.demo.boot.common.tool.CollectionTool;
import com.tcl.demo.boot.dal.user.dataobject.McIdentityAccountConfigDataObject;
import com.tcl.demo.boot.dal.user.mapper.McIdentityAccountConfigMapper;
import com.tcl.demo.boot.dal.user.type.McAccountTypeEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import com.tcl.demo.boot.dal.user.type.McValidConfigEnum;
import com.tcl.demo.boot.service.user.bo.McIdentityAccountConfigBO;
import com.tcl.demo.boot.service.user.bo.McIdentityAccountConfigTransform;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AccountInitConfigComponent extends AbstractCache<Integer, List<McIdentityAccountConfigBO>> {


    @Resource
    McIdentityAccountConfigMapper mcIdentityAccountConfigMapper;

    /**
     * 查询用户类型对应的初始化账户类型配置
     *
     * @param userType
     * @return
     */
    public List<McAccountTypeEnum> acquireValidInitAccountType(Integer userType) {

        List<McIdentityAccountConfigBO> configBOs = super.acquireValue(userType);
        if (CollectionTool.isEmpty(configBOs)) {
            return Lists.newArrayList();
        }

        configBOs = configBOs.stream()
                .filter(t -> null != t.getDefaultInit() && McValidConfigEnum.VALID_OPEN.getType().intValue() == t.getDefaultInit())
                .filter(t1 -> null != t1.getStatus() && McValidConfigEnum.VALID_OPEN.getType().intValue() == t1.getStatus())
                .collect(Collectors.toList());


        List<McAccountTypeEnum> result = configBOs.stream()
                .filter(t0 -> null != McAccountTypeEnum.initConfig.get(t0.getAccountType()))
                .map(t1 -> McAccountTypeEnum.initConfig.get(t1.getAccountType()))
                .collect(Collectors.toList());


        return result;
    }

    @Override
    protected List<McIdentityAccountConfigBO> loadValue(Integer key) {

        /**
         * 这里只是直接查询DB
         * 优化：这里可以加入redis，构建分布式缓存,注意缓存失效
         * {@link AbstractCache}
         */

        McUserTypeEnum userTypeEnum = McUserTypeEnum.acquireByType(key);
        if (null == userTypeEnum) {
            log.info("加载身份账户配置，用户类型为null，返回空集合");
            return Lists.newArrayList();
        }

        List<McIdentityAccountConfigDataObject> dbConfig =
                Optional.fromNullable(mcIdentityAccountConfigMapper.queryInitConfigByUserType(key))
                        .or(Lists.newArrayList());

        log.info("加载身份账户配置，用户类型[{}]，配置内容[{}]", key, dbConfig);

        return McIdentityAccountConfigTransform.do2bo(dbConfig);
    }

}
