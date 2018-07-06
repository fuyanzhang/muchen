package com.muchen.server.annotation;

import org.springframework.stereotype.Service;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务发布注解
 * 将此注解放到类上，则认为需要发布该服务为远程接口
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface ExportService {
    /**
     * bean id 服务名称
     * @return
     */
    String value();

    /**
     * 是否限流
     * @return
     */
    boolean rateLimiter() default false;

    /**
     * 服务分组
     * @return
     */
    String group() default "";

}
