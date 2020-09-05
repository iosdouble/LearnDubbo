package com.nh.dubbo.rpc;

import java.util.Map;

/**
 * @Classname Invocation
 * @Description TODO
 * @Date 2020/9/5 11:15 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Invocation @see support-dubbo
 */
public interface Invocation {
    /**
     * get method name.
     *
     * @return method name.
     */
    String getMethodName();

    /**
     * get parameter types.
     *
     * @return parameter types.
     */
    Class<?>[] getParameterTypes();

    /**
     * get arguments.
     *
     * @return arguments.
     */
    Object[] getArguments();

    /**
     * get attachments.
     *
     * @return attachments.
     */
    Map<String, String> getAttachments();
}
