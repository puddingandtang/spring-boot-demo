package com.tcl.demo.boot.dal.coupon.mapper;

import com.tcl.demo.boot.dal.coupon.dataobject.McAccountCouponPackageDataObject;
import org.apache.ibatis.annotations.Param;

public interface McAccountCouponPackageMapper {

    /**
     * 查询指定券
     *
     * @param accountNo
     * @param accountType
     * @param couponNo
     * @return
     */
    McAccountCouponPackageDataObject queryByCouponNo(
            @Param("accountNo") String accountNo,
            @Param("accountType") Integer accountType,
            @Param("couponNo") String couponNo);
}
