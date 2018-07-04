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
@Target(ElementType.ANNOTATION_TYPE.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
public @interface ExportService {
    String name() default "";
    boolean rateLimiter() default false;
}
