package com.tcl.demo.boot.common.tool;

import java.util.Collection;
import java.util.Map;

public abstract class CollectionTool {

    public CollectionTool() {
    }

    public static final boolean isEmpty(Collection<?> verify) {

        return verify == null || verify.isEmpty();
    }

    public static final boolean isEmpty(Map<?, ?> verify) {

        return verify == null || verify.isEmpty();
    }
}
