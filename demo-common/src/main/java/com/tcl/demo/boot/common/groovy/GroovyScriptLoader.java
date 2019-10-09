package com.tcl.demo.boot.common.groovy;

import com.google.common.collect.Maps;
import com.tcl.demo.boot.common.base.ErrorCodes;
import com.tcl.demo.boot.common.exception.BizNormalException;
import com.tcl.demo.boot.common.exception.BizRuntimeException;
import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

@Slf4j
public abstract class GroovyScriptLoader {

    /**
     * class缓存
     * 避免脚本多次编译
     * key：GroovyObjectHolder#scriptNo
     * value：class
     */
    protected final Map<Long, Class> classCache = Maps.newConcurrentMap();

    /**
     * GroovyObjectHolder缓存
     * key：GroovyObjectHolder#scriptNo
     * value：GroovyObjectHolder
     */
    protected final Map<Long, GroovyObjectHolder> groovyObjectHolderCache = Maps.newConcurrentMap();

    /**
     * 获取
     *
     * @param scriptNo
     * @return
     */
    public GroovyObjectHolder acquireGroovyObjectForScriptNo(final Long scriptNo) {

        if (null == scriptNo) {

            // 返回Mock
            return GroovyObjectHolder.builder().scriptNo(GroovyObjectHolder.NOT_EXIST).build();
        }

        // 缓存中获取
        GroovyObjectHolder cacheHolder = groovyObjectHolderCache.get(scriptNo);
        if (null != cacheHolder && cacheHolder.allCompliance()) {

            return cacheHolder;
        }

        synchronized (groovyObjectHolderCache) {

            // 重新加载获取
            GroovyObjectHolder loadHolder = load(scriptNo);
            if (null == loadHolder || !loadHolder.baseCompliance()) {

                // 返回Mock
                return GroovyObjectHolder.builder().scriptNo(GroovyObjectHolder.NOT_EXIST).build();
            }

            GroovyObject createGroovyObject = null;

            try {

                createGroovyObject = generationGroovyObject(loadHolder);

            } catch (Exception e) {

                if (e instanceof BizRuntimeException) {

                    log.warn("GroovyScriptLoader-动态生成GroovyObject失败，脚本编号[{}]，异常内容：", scriptNo, e);

                } else {

                    log.error("GroovyScriptLoader-动态生成GroovyObject失败，脚本编号[{}]，异常内容：", scriptNo, e);
                }

            }

            if (null == createGroovyObject) {

                return GroovyObjectHolder.builder().scriptNo(GroovyObjectHolder.NOT_EXIST).build();
            }

            // 构建GroovyObjectHolder
            loadHolder.setGroovyObject(createGroovyObject);

            // 构建或者覆盖本地缓存
            groovyObjectHolderCache.put(scriptNo, loadHolder);

            return loadHolder;
        }
    }

    /**
     * 清理所有缓存
     */
    public void clearALlCache() {

        synchronized (groovyObjectHolderCache) {

            groovyObjectHolderCache.clear();
        }

        synchronized (classCache) {

            classCache.clear();
        }
    }

    /**
     * 清理
     *
     * @param scriptNo
     */
    public void clearClassCache(Long scriptNo) {

        if (null == scriptNo) {

            return;
        }
        synchronized (classCache) {

            if (null != classCache.get(scriptNo)) {

                classCache.remove(scriptNo);
            }
        }
    }

    /**
     * 清理
     *
     * @param scriptNo
     */
    public void clearHolderCache(Long scriptNo) {

        if (null == scriptNo) {

            return;
        }
        synchronized (groovyObjectHolderCache) {

            if (null != groovyObjectHolderCache.get(scriptNo)) {

                groovyObjectHolderCache.remove(scriptNo);
            }
        }
    }

    /**
     *
     * @return
     */
    public Map<Long, Class> getClassCache() {
        return classCache;
    }

    /**
     *
     * @return
     */
    public Map<Long, GroovyObjectHolder> getGroovyObjectHolderCache() {
        return groovyObjectHolderCache;
    }

    /**
     * 获取GroovyObjectHolder
     * {@link com.tcl.demo.boot.common.groovy.GroovyObjectHolder#setScriptNo(Long)}
     * {@link com.tcl.demo.boot.common.groovy.GroovyObjectHolder#setScriptMD5(String)}
     * {@link com.tcl.demo.boot.common.groovy.GroovyObjectHolder#setScriptTxt(String)}
     *
     * @param scriptNo
     * @return
     */
    protected abstract GroovyObjectHolder load(Long scriptNo);

    /**
     * 生成GroovyObject
     * 避免多次编译，构建本地缓存
     *
     * @param objectHolder
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    private GroovyObject generationGroovyObject(GroovyObjectHolder objectHolder) throws IllegalAccessException, InstantiationException {

        if (null == objectHolder || !objectHolder.baseCompliance()) {
            throw new BizNormalException(ErrorCodes.GROOVY_SCRIPT_ILLEGALITY, "脚本内容非法");
        }

        Long scriptNo = objectHolder.getScriptNo();
        String scriptTxt = objectHolder.getScriptTxt();

        Class<?> cacheClass = acquireClassCache(scriptNo);
        if (null != cacheClass) {

            return (GroovyObject) cacheClass.newInstance();
        }

        ClassLoader parent = Thread.currentThread().getContextClassLoader();

        GroovyClassLoader classLoader = new GroovyClassLoader(parent);

        // clazz 不同，这里每次都会进行一次脚本编译，最好进行缓存，除非脚本发生变化
        Class<?> clazz = classLoader.parseClass(scriptTxt);

        classCache.put(scriptNo, clazz);

        GroovyObject object = (GroovyObject) clazz.newInstance();

        return object;
    }


    private Class<?> acquireClassCache(Long scriptNo) {

        Class<?> md5Class = classCache.get(scriptNo);
        if (null != md5Class) {

            return md5Class;
        }

        return null;
    }

}
