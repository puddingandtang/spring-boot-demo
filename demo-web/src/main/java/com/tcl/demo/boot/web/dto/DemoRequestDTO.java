package com.tcl.demo.boot.web.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
public class DemoRequestDTO extends BaseRequestDTO implements Serializable {

    private static final long serialVersionUID = 6130732824819680615L;
}
