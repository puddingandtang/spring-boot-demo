package com.tcl.demo.boot.common.cache;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.base.ErrorLv;
import com.tcl.demo.boot.common.preconditions.PreconditionsAssert;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 基于guava cache封装
 * 本地缓存，适用在单机
 * 但是在{@link AbstractCache#loadValue}是子类自由实现，可以在实现中引入redis，那么二级缓存则变为分布式缓存，如果二级缓存没命中，则查询DB。
 * 一般都采用分布式缓存，那么需要注意如下几点：
 * 1.redis的缓存失效时间和guava的失效时间需要合理规划，理论上redis缓存失效时间大于guava构建的内存缓存。
 * 2.缓存的失效需要注意先清楚redis的缓存，再清除guava构建的内存缓存。
 * 3.一般失效缓存通过消息（例如kafka），那么要关注事务操作和消息失效通知不应为一个整体，事务提交后再进行缓存失效通知，而且需要通知到每一台服务器上。
 */
@Slf4j(topic = "cache")
public abstract class AbstractCache<K, V> {

    private static final long DEFAULT_SIZE = 128L;

    private static final long DEFAULT_EXPIRE_DURATION = 60;

    private static Object NULL = new Object();

    private Cache<K, Object> cache;

    /**
     * 默认size {@link AbstractCache#DEFAULT_SIZE}
     * 默认expire {@link AbstractCache#DEFAULT_EXPIRE_DURATION}
     */
    public AbstractCache() {

        this.cache = this.initCache(DEFAULT_SIZE, DEFAULT_EXPIRE_DURATION);
    }

    /**
     * @param maxSize        缓存大小
     * @param expireDuration 失效时长
     */
    public AbstractCache(Long maxSize, Long expireDuration) {

        PreconditionsAssert.assertTrue(null != maxSize && maxSize.longValue() > 0L, ErrorCodes.INIT_PARAM_ILLEGALITY, ErrorLv.ERROR);
        PreconditionsAssert.assertTrue(null != expireDuration && expireDuration > 0L, ErrorCodes.INIT_PARAM_ILLEGALITY, ErrorLv.ERROR);

        this.cache = this.initCache(maxSize, expireDuration);
    }

    private Cache<K, Object> initCache(Long maxSize, Long expireDuration) {

        return CacheBuilder.newBuilder()
                .maximumSize(maxSize)
                .expireAfterWrite(expireDuration, TimeUnit.SECONDS).build();
    }

    /**
     * 获取value
     *
     * @param key 如果非法返回null
     * @return
     */
    public V acquireValue(K key) {

        if (null == key) {
            log.info("获取缓存，key为null，value直接返回null");
            return null;
        }

        try {

            /**
             *  https://github.com/google/guava/wiki/CachesExplained
             *  key失效 {@link LocalCache.Segment#getLiveValue(com.google.common.cache.LocalCache.ReferenceEntry, long)}
             *  返回值为null 将抛出异常 {@link com.google.common.cache.LocalCache.Segment#getAndRecordStats}
             */
            Object result = cache.get(key, callBack(key));

            if (result == NULL) {
                log.info("获取缓存，key-[{}],value-为Mock的临时缓存，最终返回null", key);
                return null;
            }

            return (V) result;

        } catch (Throwable ee) {

            // com.google.common.util.concurrent.ExecutionError
            // com.google.common.util.concurrent.UncheckedExecutionException
            // java.util.concurrent.ExecutionException
            // throw new RuntimeException(ee.getMessage(), ee);

            log.error("获取缓存，key-[" + JSON.toJSONString(key) + "]值异常，返回null，异常内容：", ee);

            // 构建该key的临时缓存
            this.cache.put(key, NULL);

            return null;
        }


    }

    /**
     * 清理指定key缓存
     *
     * @param key
     */
    public void cleanValue(K key) {
        this.cache.invalidate(key);
    }

    /**
     * 清理所有缓存
     */
    public void cleanAllValue() {
        this.cache.invalidateAll();
    }

    protected Callable<Object> callBack(K key) {

        return new Callable<Object>() {
            @Override
            public Object call() throws Exception {

                Object result = loadValue(key);

                if (null == result) {

                    return NULL;
                }

                return result;
            }
        };
    }


    /**
     * 加载数据，由子类实现
     */
    protected abstract V loadValue(K key);

}
