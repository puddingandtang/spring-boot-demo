package com.tcl.demo.boot.service.user;

import com.tcl.demo.boot.service.user.bo.McAccountBO;

import java.util.List;

public interface McAccountService {

    /**
     * 查询&默认创建
     *
     * @param identityNo
     * @return 返回值有可能为空集合，请注意
     */
    List<McAccountBO> queryAccountInfoOrDefaultCreate(String identityNo, Integer userType);

    /**
     * 查询&创建
     *
     * @param identityNo
     * @param accountType
     * @return
     */
    McAccountBO queryAccountInfoOrCreate(String identityNo, Integer accountType);
}
