package com.tcl.demo.boot.dal.user.mapper;

import com.tcl.demo.boot.dal.user.dataobject.McAccountInfoDataObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface McAccountInfoMapper {

    /**
     * 查询身份下的账户列表
     *
     * @param identityNo
     * @return
     */
    List<McAccountInfoDataObject> queryAccountInfoByIdentityNo(@Param("identityNo") String identityNo);

    /**
     * 查询账户信息
     *
     * @param accountNo
     * @return
     */
    McAccountInfoDataObject queryAccountInfoByAccountNo(@Param("accountNo") String accountNo);

    /**
     * 查询身份下指定的账户信息
     *
     * @param identityNo
     * @param accountType
     * @return
     */
    McAccountInfoDataObject queryAccountInfoByIdentityNoOne(@Param("identityNo") String identityNo,
                                                            @Param("accountType") Integer accountType);

    /**
     * 插入
     *
     * @param createDataList
     * @return
     */
    int createAccountInfos(@Param("createDataList") List<McAccountInfoDataObject> createDataList);
}
