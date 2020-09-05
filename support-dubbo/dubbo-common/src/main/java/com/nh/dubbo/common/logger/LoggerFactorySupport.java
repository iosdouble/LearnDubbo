package com.nh.dubbo.common.logger;

import java.io.File;

/**
 * @Classname LoggerFactorySupport
 * @Description TODO 日志输出支持器
 * @Date 2020/9/5 10:34 AM
 * @Created by nihui
 * @Version 1.0
 * @Description LoggerFactorySupport @see support-dubbo
 */
public interface LoggerFactorySupport {

    /**
     * 获取日志输出器
     *
     * @param key 分类键
     * @return 日志输出器, 后验条件: 不返回null.
     */
    Logger getLogger(Class<?> key);

    /**
     * 获取日志输出器
     *
     * @param key 分类键
     * @return 日志输出器, 后验条件: 不返回null.
     */
    Logger getLogger(String key);

    /**
     * 设置输出等级
     *
     * @param level
     */
    void setLevel(Level level);

    /**
     *
     * @return
     */
    Level getLevel();

    /**
     *
     * @return
     */
    File getFile();

    /**
     *
     * @param file
     */
    void setFile(File file);
}
