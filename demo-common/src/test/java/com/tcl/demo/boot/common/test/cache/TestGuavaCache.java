package com.tcl.demo.boot.common.test.cache;

import com.google.common.collect.Lists;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.cache.AbstractCache;
import com.tcl.demo.boot.common.exception.BizNormalException;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class TestGuavaCache extends AbstractCache<Integer, List<String>> {

    public TestGuavaCache() {
        super(1L, 20L);
    }

    @Override
    protected List<String> loadValue(Integer key) {


        if (0 == key.intValue()) {
            // 测试主动抛出异常
            log.info("缓存key[{}],测试主动抛出异常", key);
            throw new BizNormalException(ErrorCodes.CODE_ERROR);
        }

        if (1 == key.intValue()) {
            // 测试代码移除
            log.info("缓存key[{}],测试代码异常", key);
            int a = 9 / 0;
        }

        if (2 == key.intValue()) {
            log.info("缓存key[{}],测试返回null", key);
            // 测试返回为null
            return null;
        }

        log.info("缓存key[{}],测试返回正常数据", key);
        return Lists.newArrayList("test1", "test2");
    }
}
