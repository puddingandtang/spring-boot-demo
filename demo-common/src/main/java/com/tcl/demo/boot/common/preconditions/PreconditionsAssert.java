package com.tcl.demo.boot.common.preconditions;

import com.tcl.demo.boot.common.base.ErrorCode;
import com.tcl.demo.boot.common.base.ErrorLv;
import com.tcl.demo.boot.common.exception.BizNormalException;

public abstract class PreconditionsAssert {

    public PreconditionsAssert() {
    }

    /**
     * @param expression
     * @param errorCode
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode) {

        if (!expression) {
            throw new BizNormalException(errorCode);
        }
    }

    /**
     * @param expression
     * @param errorCode
     * @param objects
     */
    public static void assertTrue(boolean expression, ErrorCode errorCode, Object... objects) {

        if (!expression) {
            throw new BizNormalException(errorCode, objects);
        }
    }

    public static void assertTrue(boolean expression, ErrorCode errorCode, ErrorLv errorLv, Object... objects) {

        if (!expression) {
            throw new BizNormalException(errorCode, errorLv, objects);
        }

    }

    /**
     * @param o
     * @param errorCode
     */
    public static void assertNotNull(Object o, ErrorCode errorCode) {

        if (null == o) {

            throw new BizNormalException(errorCode);
        }
    }

    /**
     * @param o
     * @param errorCode
     * @param objects
     */
    public static void assertNotNull(Object o, ErrorCode errorCode, Object... objects) {

        if (null == o) {

            throw new BizNormalException(errorCode, objects);
        }
    }

    /**
     * @param o
     * @param errorCode
     * @param errorLv
     * @param objects
     */
    public static void assertNotNull(Object o, ErrorCode errorCode, ErrorLv errorLv, Object... objects) {

        if (null == o) {

            throw new BizNormalException(errorCode, errorLv, objects);
        }
    }


}
