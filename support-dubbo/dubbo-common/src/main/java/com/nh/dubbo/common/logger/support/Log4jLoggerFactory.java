package com.nh.dubbo.common.logger.support;

import com.nh.dubbo.common.logger.Logger;
import com.nh.dubbo.common.logger.LoggerFactorySupport;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;

import java.io.File;
import java.util.Enumeration;

/**
 * @Classname Log4jLoggerFactory
 * @Description TODO
 * @Date 2020/9/5 10:41 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Log4jLoggerFactory @see support-dubbo
 */
public class Log4jLoggerFactory implements LoggerFactorySupport {
    private File file;

    @SuppressWarnings("unchecked")
    public Log4jLoggerFactory() {
        try {
            org.apache.log4j.Logger logger = LogManager.getRootLogger();
            if (logger != null) {
                Enumeration<Appender> appenders = logger.getAllAppenders();
                if (appenders != null) {
                    while (appenders.hasMoreElements()) {
                        Appender appender = appenders.nextElement();
                        if (appender instanceof FileAppender) {
                            FileAppender fileAppender = (FileAppender)appender;
                            String filename = fileAppender.getFile();
                            file = new File(filename);
                            break;
                        }
                    }
                }
            }
        } catch (Throwable t) {
        }
    }

    public Logger getLogger(Class<?> key) {
        return new Log4jLogger(LogManager.getLogger(key));
    }

    public Logger getLogger(String key) {
        return new Log4jLogger(LogManager.getLogger(key));
    }

    public void setLevel(Level level) {
        LogManager.getRootLogger().setLevel(toLog4jLevel(level));
    }

    public Level getLevel() {
        return fromLog4jLevel(LogManager.getRootLogger().getLevel());
    }

    public File getFile() {
        return file;
    }

    private static org.apache.log4j.Level toLog4jLevel(Level level) {
        if (level == Level.ALL)
            return org.apache.log4j.Level.ALL;
        if (level == Level.TRACE)
            return org.apache.log4j.Level.TRACE;
        if (level == Level.DEBUG)
            return org.apache.log4j.Level.DEBUG;
        if (level == Level.INFO)
            return org.apache.log4j.Level.INFO;
        if (level == Level.WARN)
            return org.apache.log4j.Level.WARN;
        if (level == Level.ERROR)
            return org.apache.log4j.Level.ERROR;
        // if (level == Level.OFF)
        return org.apache.log4j.Level.OFF;
    }

    private static Level fromLog4jLevel(org.apache.log4j.Level level) {
        if (level == org.apache.log4j.Level.ALL)
            return Level.ALL;
        if (level == org.apache.log4j.Level.TRACE)
            return Level.TRACE;
        if (level == org.apache.log4j.Level.DEBUG)
            return Level.DEBUG;
        if (level == org.apache.log4j.Level.INFO)
            return Level.INFO;
        if (level == org.apache.log4j.Level.WARN)
            return Level.WARN;
        if (level == org.apache.log4j.Level.ERROR)
            return Level.ERROR;
        // if (level == org.apache.log4j.Level.OFF)
        return Level.OFF;
    }

    public void setFile(File file) {

    }
}
