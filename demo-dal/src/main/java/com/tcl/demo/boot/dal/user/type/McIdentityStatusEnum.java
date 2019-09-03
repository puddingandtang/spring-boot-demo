package com.tcl.demo.boot.dal.user.type;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum McIdentityStatusEnum {

    INITIALIZE(1, "初始化状态"),
    USABLE(2, "可用状态"),
    FREEZE(3, "冻结状态"),
    DESTROY(9, "注销状态");

    McIdentityStatusEnum(Integer statusCode, String statusDesc) {
        this.statusCode = statusCode;
        this.statusDesc = statusDesc;
    }

    private Integer statusCode;

    private String statusDesc;

    /**
     * 获取状态枚举
     *
     * @param statusCode
     * @return
     */
    public static final McIdentityStatusEnum acquireByStatusCode(final Integer statusCode) {

        if (null == statusCode) {
            return null;
        }

        return Arrays.stream(McIdentityStatusEnum.values())
                .filter(t -> t.getStatusCode().intValue() == statusCode.intValue())
                .findFirst()
                .orElse(null);
    }
}
