package com.yjx.app.annotation;

import java.lang.annotation.*;

/**
 * <p> 
 * token验证注解
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/18 14:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenValid {

    boolean requireToken() default true;
}
