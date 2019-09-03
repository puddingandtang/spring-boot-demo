package com.tcl.demo.boot.web.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class IdentityRequestDTO extends BaseDTO implements Serializable {

    private static final long serialVersionUID = 3056457025673057914L;

    @Builder
    public IdentityRequestDTO(String userNo, Integer userType, Integer bizLine, Integer terminal) {
        super(userNo, userType, bizLine, terminal);
    }
}
