package com.tcl.demo.boot.web.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础BaseRequestDTO
 */
@Getter
@Setter
public class BaseRequestDTO implements Serializable {

    /**
     * 用户编号
     */
    private Long userNo;

    /**
     * 用户类型
     */
    private Integer userType;

    /**
     * 业务线编码
     */
    private Integer bizLine;

    /**
     * 终端
     */
    private Integer terminal;

}
