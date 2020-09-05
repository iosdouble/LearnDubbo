package com.nh.dubbo.common;

import java.lang.annotation.*;

/**
 * @Classname Extension
 * @Description TODO 扩展点实现的元信息
 * @Date 2020/9/5 10:19 AM
 * @Created by nihui
 * @Version 1.0
 * @Description Extension @see support-dubbo
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Extension {

    /**
     * 扩展点名称
     *
     * 如果注解在扩展的接口上，则是缺省的扩展点
     * @return
     */
    String value() default "";
}
