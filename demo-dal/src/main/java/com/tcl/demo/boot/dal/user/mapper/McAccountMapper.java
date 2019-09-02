package com.tcl.demo.boot.dal.user.mapper;

import com.tcl.demo.boot.dal.user.dataobject.McAccountDataObject;
import org.apache.ibatis.annotations.Param;

public interface McAccountMapper {

    /**
     * 查询账户信息
     *
     * @param accountNo
     * @param accountType
     * @return
     */
    McAccountDataObject queryByAccountNo(
            @Param("accountNo") String accountNo,
            @Param("accountType") Integer accountType);
}
