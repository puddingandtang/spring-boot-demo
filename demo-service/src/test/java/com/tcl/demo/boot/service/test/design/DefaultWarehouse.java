package com.tcl.demo.boot.service.test.design;

import com.google.common.collect.Lists;

import java.util.List;

public class DefaultWarehouse extends Warehouse {

    public DefaultWarehouse(int size) {

        super(Lists.newArrayListWithCapacity(size));
    }

    @Override
    public Entry put(Entry entry) {


        return null;
    }

    @Override
    public Entry get(String outType) {
        return null;
    }
}
