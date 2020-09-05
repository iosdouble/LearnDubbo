package com.nh.dubbo.rpc;

import com.nh.dubbo.common.Adaptive;
import com.nh.dubbo.common.Extension;
import com.nh.dubbo.common.URL;

/**
 * @Classname Protocol
 * @Description TODO
 * @Date 2020/9/5 10:18 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Protocol @see support-dubbo
 */
@Extension
public interface Protocol {
    /**
     * 获取默认端口号
     * @return
     */
    public int getDefaultPort();

    /**
     * export.
     *
     * @param <T>
     * @param invoker
     * @return exporter
     */
    @Adaptive
    <T> Exporter<T> export(Invoker<T> invoker) throws RpcException;

    /**
     * refer.
     *
     * @param <T>
     * @param type
     * @param url
     * @return invoker
     * @throws RpcException
     */
    @Adaptive
    <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException;

    /**
     * destory.
     */
    void destroy();

}
