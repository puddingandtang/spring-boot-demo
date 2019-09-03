package com.tcl.demo.boot.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class IdentityResponseDTO extends BaseDTO implements Serializable {

    private String identityNo;

    private Integer status;

    @Builder
    public IdentityResponseDTO(String userNo, Integer userType, Integer bizLine, Integer terminal, String identityNo, Integer status) {
        super(userNo, userType, bizLine, terminal);
        this.identityNo = identityNo;
        this.status = status;
    }
}
