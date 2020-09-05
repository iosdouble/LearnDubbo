package com.nh.dubbo.rpc;

/**
 * @Classname Exporter
 * @Description TODO 需要暴露的接口地址
 * @Date 2020/9/5 10:28 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Exporter @see support-dubbo
 */
public interface Exporter<T> {

    /**
     * get invoker.
     *
     * @return invoker
     */
    Invoker<T> getInvoker();

    /**
     * unexport.
     *
     * <code>
     *     getInvoker().destroy();
     * </code>
     */
    void unexport();
}
