package com.tcl.demo.boot.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DemoRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 6130732824819680615L;

    @Builder
    public DemoRequestDTO(String userNo, Integer userType, Integer bizLine, Integer terminal) {
        super(userNo, userType, bizLine, terminal);
    }
}
