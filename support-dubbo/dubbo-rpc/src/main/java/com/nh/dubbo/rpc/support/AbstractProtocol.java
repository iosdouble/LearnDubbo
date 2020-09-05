package com.nh.dubbo.rpc.support;

import com.nh.dubbo.common.Constants;
import com.nh.dubbo.common.URL;
import com.nh.dubbo.common.logger.Logger;
import com.nh.dubbo.common.logger.LoggerFactory;
import com.nh.dubbo.common.utils.ConcurrentHashSet;
import com.nh.dubbo.rpc.Exporter;
import com.nh.dubbo.rpc.Invoker;
import com.nh.dubbo.rpc.Protocol;
import com.nh.dubbo.rpc.RpcConstants;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname AbstractProtocol
 * @Description TODO
 * @Date 2020/9/5 10:30 AM
 * @Created by nihui
 * @Version 1.0
 * @Description AbstractProtocol @see support-dubbo
 */
public abstract class AbstractProtocol implements Protocol {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Map<String,Exporter<?>> exporterMap = new ConcurrentHashMap<String, Exporter<?>>();

    protected final Set<Invoker<?>> invokers = new ConcurrentHashSet<Invoker<?>>();

    protected static String serviceKey(URL url) {
        return serviceKey(url.getPort(), url.getPath(), url.getParameter(Constants.VERSION_KEY),
                url.getParameter(Constants.GROUP_KEY));
    }

    protected static String serviceKey(int port, String serviceName, String serviceVersion, String serviceGroup) {
        StringBuilder buf = new StringBuilder();
        if (serviceGroup != null && serviceGroup.length() > 0) {
            buf.append(serviceGroup);
            buf.append("/");
        }
        buf.append(serviceName);
        if (serviceVersion != null && serviceVersion.length() > 0 && ! "0.0.0".equals(serviceVersion)) {
            buf.append(":");
            buf.append(serviceVersion);
        }
        buf.append(":");
        buf.append(port);
        return buf.toString();
    }

    public void destroy() {
        for (Invoker<?> invoker : invokers){
            if (invoker != null) {
                invokers.remove(invoker);
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("Destroy reference: " + invoker.getUrl());
                    }
                    invoker.destroy();
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
        for (String key : new ArrayList<String>(exporterMap.keySet())) {
            Exporter<?> exporter = exporterMap.remove(key);
            if (exporter != null) {
                try {
                    if (logger.isInfoEnabled()) {
                        logger.info("Unexport service: " + exporter.getInvoker().getUrl());
                    }
                    exporter.unexport();
                } catch (Throwable t) {
                    logger.warn(t.getMessage(), t);
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    protected static int getServerShutdownTimeout() {
        int timeout = RpcConstants.DEFAULT_SERVER_SHUTDOWN_TIMEOUT;
        String value = System.getProperty(RpcConstants.SHUTDOWN_TIMEOUT_KEY);
        if (value != null && value.length() > 0) {
            try{
                timeout = Integer.parseInt(value);
            }catch (Exception e) {
            }
        } else {
            value = System.getProperty(RpcConstants.SHUTDOWN_TIMEOUT_SECONDS_KEY);
            if (value != null && value.length() > 0) {
                try{
                    timeout = Integer.parseInt(value) * 1000;
                }catch (Exception e) {
                }
            }
        }

        return timeout;
    }
}
