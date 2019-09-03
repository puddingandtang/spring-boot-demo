package com.tcl.demo.boot.dal.user.mapper;

import com.tcl.demo.boot.dal.user.dataobject.McIdentityInfoDataObject;
import org.apache.ibatis.annotations.Param;

public interface McIdentityInfoMapper {

    /**
     * 创建营销身份
     *
     * @param createIdentity
     * @return
     */
    int createIdentityInfo(McIdentityInfoDataObject createIdentity);

    /**
     * 查询营销身份
     *
     * @return
     */
    McIdentityInfoDataObject queryIdentityInfo(@Param("userNo") String userNo, @Param("userType") Integer userType);
}
