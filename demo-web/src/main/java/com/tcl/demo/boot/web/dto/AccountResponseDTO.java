package com.tcl.demo.boot.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class AccountResponseDTO extends BaseDTO implements Serializable {

    private String identityNo;

    private String accountNo;

    private Integer accountType;

    private Integer status;

    @Builder
    public AccountResponseDTO(String userNo, Integer userType, Integer bizLine, Integer terminal, String identityNo, String accountNo, Integer accountType, Integer status) {
        super(userNo, userType, bizLine, terminal);
        this.identityNo = identityNo;
        this.accountNo = accountNo;
        this.accountType = accountType;
        this.status = status;
    }
}
