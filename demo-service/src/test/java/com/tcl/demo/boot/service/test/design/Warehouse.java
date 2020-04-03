package com.tcl.demo.boot.service.test.design;

import org.springframework.util.CollectionUtils;

import java.util.List;

public abstract class Warehouse {

    private List<Entry> entries;

    /**
     * 入库
     *
     * @return
     */
    public boolean in(Entry entry) {

        if (null == entry) {

            return false;
        }

        // 子类实现
        put(entry);

        return false;
    }

    public Entry out(String outType) {

        get(outType);

        return null;
    }


    protected abstract Entry put(Entry entry);

    protected abstract Entry get(String outType);

    public Warehouse(List<Entry> entries) {

        if (CollectionUtils.isEmpty(entries)) {

            throw new RuntimeException("设置仓库容器非法");
        }

        this.entries = entries;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
