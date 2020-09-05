package com.nh.dubbo.rpc;

/**
 * @Classname Result
 * @Description TODO
 * @Date 2020/9/5 11:14 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Result @see support-dubbo
 */
public interface Result {
    /**
     * Has exception.
     *
     * @return has exception.
     */
    boolean hasException();

    /**
     * Get invoke result.
     *
     * @return result if has exception throw it.
     * @throws Throwable
     */
    Object getResult();

    /**
     * Get exception.
     *
     * @return exception if no exception return null.
     */
    Throwable getException();

    /**
     * Recreate.
     *
     * <code>
     * if (hasException()) {
     *     throw getException();
     * } else {
     *     return getResult();
     * }
     * </code>
     */
    Object recreate() throws Throwable;
}
