package com.tcl.demo.boot.common.test.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Ignore;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

@Slf4j
@Ignore
public class GuavaCacheTest {

    @Test
    public void guavaCache() {

        try {

            long expireDuration = 1000L;

            Cache<Integer, String> cache = CacheBuilder
                    .newBuilder()
                    .expireAfterWrite(expireDuration, TimeUnit.MILLISECONDS)
                    .build();

            log.info("初始化缓存，缓存数据大小:{}", cache.size());
            cache.put(new Integer(1), "1");
            log.info("构建一个缓存，缓存数据大小:{}", cache.size());
            Thread.sleep(expireDuration * 2);
            log.info("睡眠超过expireAfterWrite配置，缓存数据大小:{}", cache.size());
            // 失效的操作是在get触发移除,当然put操作也会进行触发移除
            log.info("睡眠超过expireAfterWrite配置，获取缓存内容:{}", cache.getIfPresent(1));

        } catch (Exception e) {

            e.printStackTrace();
        }


    }

    @Test
    public void testGuavaCache() {

        TestGuavaCache cache = new TestGuavaCache();

        log.info("获取结果---》{}\n", cache.acquireValue(0));
        log.info("获取结果---》{}\n", cache.acquireValue(0));
        //log.info("获取结果---》{}\n", cache.acquireValue(1));
        //log.info("获取结果---》{}\n", cache.acquireValue(2));
        //log.info("获取结果---》{}\n", cache.acquireValue(3));
        //log.info("获取结果---》{}\n", cache.acquireValue(3));

    }
}
