package com.nh.dubbo.common.logger;

import com.nh.dubbo.common.logger.support.FailsafeLogger;
import com.nh.dubbo.common.logger.support.JdkLoggerFactory;
import com.nh.dubbo.common.logger.support.Log4jLoggerFactory;

import java.io.File;

/**
 * @Classname LoggerFactory
 * @Description TODO 日志输出工厂
 * @Date 2020/9/5 10:34 AM
 * @Created by nihui
 * @Version 1.0
 * @Description LoggerFactory @see support-dubbo
 */
public class LoggerFactory {
    private LoggerFactory() {
    }

    private static volatile LoggerFactorySupport LOGGER_FACTORY;

    // 查找常用的日志框架
    static {
        try {
            setLoggerFactory(new Log4jLoggerFactory());
        } catch (Throwable e1) {
            setLoggerFactory(new JdkLoggerFactory());
        }
    }

    /**
     * 设置日志输出器供给器
     *
     * @param loggerFactory
     *            日志输出器供给器
     */
    public static void setLoggerFactory(LoggerFactorySupport loggerFactory) {
        if (loggerFactory != null) {
            Logger logger = loggerFactory.getLogger(LoggerFactory.class.getName());
            logger.info("using logger: " + loggerFactory.getClass().getName());
            LoggerFactory.LOGGER_FACTORY = loggerFactory;
        }
    }

    /**
     * 获取日志输出器
     *
     * @param key
     *            分类键
     * @return 日志输出器, 后验条件: 不返回null.
     */
    public static Logger getLogger(Class<?> key) {
        return new FailsafeLogger(LOGGER_FACTORY.getLogger(key));
    }

    /**
     * 获取日志输出器
     *
     * @param key
     *            分类键
     * @return 日志输出器, 后验条件: 不返回null.
     */
    public static Logger getLogger(String key) {
        return new FailsafeLogger(LOGGER_FACTORY.getLogger(key));
    }

    /**
     * 动态设置输出日志级别
     *
     * @param level 日志级别
     */
    public static void setLevel(Level level) {
        LOGGER_FACTORY.setLevel(level);
    }

    /**
     * 获取日志级别
     *
     * @return 日志级别
     */
    public static Level getLevel() {
        return LOGGER_FACTORY.getLevel();
    }

    /**
     * 获取日志文件
     *
     * @return 日志文件
     */
    public static File getFile() {
        return LOGGER_FACTORY.getFile();
    }
}
