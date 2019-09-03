package com.tcl.demo.boot.service.user.component;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.dal.user.type.McAccountTypeEnum;
import com.tcl.demo.boot.dal.user.type.McUserTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class AccountInitConfigComponent {

    /**
     * 查询用户类型对应的初始化账户类型配置
     *
     * @param userType
     * @return
     */
    public List<McAccountTypeEnum> acquireInitAccountType(Integer userType) {

        McUserTypeEnum userTypeEnum = McUserTypeEnum.acquireByType(userType);

        if (null == userTypeEnum) {

            return Lists.newArrayList();
        }

        // todo 查询账户初始化配置
        // todo 采用guava + redis
        // todo 注意缓存失效

        List<McAccountTypeEnum> mockData = Lists.newArrayList(McAccountTypeEnum.COUPON, McAccountTypeEnum.INTEGRAL);

        return mockData;
    }

}
