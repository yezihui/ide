package com.yjx.app.aop;

import cn.hutool.core.annotation.AnnotationUtil;
import cn.hutool.core.util.StrUtil;
import com.yjx.app.annotation.TokenValid;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * <p>
 * token验证aop拦截
 * </p>
 *
 * @author yejx
 * @date 2019/9/18 14:54
 */
@Slf4j
@Aspect
public class ChainOnRequestHeaderAop {

    private static final String HEAD_TOKEN = "token";

    /**
     * 拦截控制器层和远程提供者层
     */
    @Pointcut("execution(public * com.yjx.app.modular.*.controller..*(..))")
    public void cutService() {
    }

    @Before("cutService()")
    public void before(JoinPoint point) throws NoSuchMethodException {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = point.getTarget().getClass().getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
        Annotation annotation = this.getOnMethod(method);
        HttpServletRequest request = getRequest();
        //访问令牌
        String token = request.getHeader(HEAD_TOKEN);

        if (annotation != null && annotation.annotationType().equals(TokenValid.class)) {
            boolean requireToken = AnnotationUtil.getAnnotationValue(method, TokenValid.class, "requireToken");
            if (requireToken) {
                //需要TOKEN验证
            } else {
                //不验证token
                if (StrUtil.isEmpty(token)) {
                    return;
                }
//                boolean valid = loginUserService.check(token);
//                if (valid) {
//
//                }
            }
        }
    }

    private static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

    @Around("cutService()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        //初始化临时保存器
        log.info("初始化临时保存器");
        try {
            Object result = point.proceed();
            log.info("请求调用成功");
            return result;
        } catch (Throwable throwable) {
            log.error("请求调用失败");
            throw throwable;
        } finally {
            log.info("清空临时保存器");
        }
    }

    /**
     * 获取token验证注解
     *
     * @param method 方法
     * @return 注解
     * @author yejx
     * @date 2019/9/19 11:13
     */
    private Annotation getOnMethod(Method method) {
        TokenValid tokenValid = method.getAnnotation(TokenValid.class);
        Annotation annotation = null;
        if (tokenValid != null) {
            annotation = tokenValid;
        }
        return annotation;
    }
}
