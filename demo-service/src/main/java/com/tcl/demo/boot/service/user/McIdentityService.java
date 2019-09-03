package com.tcl.demo.boot.service.user;

import com.tcl.demo.boot.service.user.bo.McIdentityBO;

public interface McIdentityService {

    /**
     * 查询营销身份信息，如果不存在则创建
     * 暂时不构建事务
     *
     * @param queryBO
     * @return
     */
    McIdentityBO queryIdentityInfoOrCreate(McIdentityBO queryBO);
}
