package com.nh.dubbo.rpc;

import com.nh.dubbo.common.URL;

/**
 * @Classname Invoker
 * @Description TODO 进行代理的实体对象
 * @Date 2020/9/5 10:28 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Invoker @see support-dubbo
 */
public interface Invoker<T> {
    /**
     * get service interface.
     *
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * get service url.
     *
     * @return service url.
     */
    URL getUrl();

    /**
     * is available.
     *
     * @return available.
     */
    boolean isAvailable();

    /**
     * invoke.
     *
     * @param invocation
     * @return result
     * @throws RpcException
     */
    Result invoke(Invocation invocation) throws RpcException;

    /**
     * destroy.
     */
    void destroy();
}
