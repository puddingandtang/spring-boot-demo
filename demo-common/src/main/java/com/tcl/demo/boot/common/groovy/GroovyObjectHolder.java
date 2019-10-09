package com.tcl.demo.boot.common.groovy;

import com.google.common.base.Strings;
import groovy.lang.GroovyObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroovyObjectHolder implements Serializable {

    public static final Long NOT_EXIST = -1L;

    /**
     * 脚本编号
     */
    private Long scriptNo;

    /**
     * 脚本内容
     */
    private String scriptTxt;

    /**
     * 脚本MD5值
     * <>
     * 校验脚本内容一致性
     * </>
     */
    private String scriptMD5;

    /**
     * GroovyObject接口
     */
    private GroovyObject groovyObject;

    /**
     * 基础合规：scriptNo ，scriptMD5 ， scriptTxt
     *
     * @return
     */
    public final boolean baseCompliance() {

        if (null == this.scriptNo || Strings.isNullOrEmpty(this.scriptMD5) || Strings.isNullOrEmpty(this.scriptTxt)) {

            return false;
        }

        return true;
    }

    /**
     * 接口合规
     *
     * @return
     */
    public final boolean allCompliance() {

        if (!baseCompliance() || null == this.groovyObject) {

            return false;
        }

        return true;

    }
}
