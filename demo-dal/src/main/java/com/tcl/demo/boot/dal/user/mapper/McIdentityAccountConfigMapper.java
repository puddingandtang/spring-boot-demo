package com.tcl.demo.boot.dal.user.mapper;

import com.tcl.demo.boot.dal.user.dataobject.McIdentityAccountConfigDataObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McIdentityAccountConfigMapper {

    /**
     * 获取账户初始化配置
     *
     * @param userType
     * @return
     */
    List<McIdentityAccountConfigDataObject> queryInitConfigByUserType(@Param("userType") Integer userType);
}
