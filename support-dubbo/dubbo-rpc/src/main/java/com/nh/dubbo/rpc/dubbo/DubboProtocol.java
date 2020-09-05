package com.nh.dubbo.rpc.dubbo;

import com.nh.dubbo.common.Extension;
import com.nh.dubbo.common.URL;
import com.nh.dubbo.rpc.Exporter;
import com.nh.dubbo.rpc.Invoker;
import com.nh.dubbo.rpc.RpcException;
import com.nh.dubbo.rpc.support.AbstractProtocol;

/**
 * @Classname DubboProtocol
 * @Description TODO
 * @Date 2020/9/5 11:19 AM
 * @Created by nihui
 * @Version 1.0
 * @Description DubboProtocol @see support-dubbo
 */
@Extension(DubboProtocol.NAME)
public class DubboProtocol extends AbstractProtocol {
    public static final String NAME = "dubbo";

    public static final String COMPATIBLE_CODEC_NAME = "dubbo1compatible";

    public static final int DEFAULT_PORT = 20880;

    public int getDefaultPort() {
        return 0;
    }

    public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
        return null;
    }

    public <T> Invoker<T> refer(Class<T> type, URL url) throws RpcException {
        return null;
    }
}
