package com.yjx.web.aop;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import com.yjx.common.util.HttpContextUtil;
import com.yjx.model.web.ro.LoginRo;
import com.yjx.web.bean.WebLoginUser;
import com.yjx.web.log.LogObjectHolder;
import com.yjx.web.log.factory.LogManager;
import com.yjx.web.log.factory.LogTaskFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 记录登录日志AOP
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 16:53
 */
@Aspect
@Slf4j
public class ChainOnLoginLogAop {

    @Data
    private class HandleConfigurer {

        private String requestNo;

        private String requestIp;
    }

    /**
     * 拦截AuthController类的login方法
     */
    @Pointcut(value = "execution(* com.yjx.web.modular.auth.controller.AuthController.login(..))")
    public void cutLoginService() {
    }

    /**
     * 拦截AuthController类的logout方法
     */
    @Pointcut(value = "execution(* com.yjx.web.modular.auth.controller.AuthController.logout(..))")
    public void cutLogoutService() {
    }

    /**
     * 拦截AuthController类的resources方法
     */
    @Pointcut(value = "execution(* com.yjx.web.modular.auth.controller.AuthController.resources(..))")
    public void cutResourceService() {

    }

    @Around("cutLoginService()")
    public Object loginAround(ProceedingJoinPoint point) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("初始化临时保存器");
        }
        // 初始化临时保存器
        LogObjectHolder.init();
        HandleConfigurer configurer = build();
        try {
            // 上述之前执行
            Object result = point.proceed();
            if (log.isDebugEnabled()) {
                log.debug("请求登录成功");
            }
            // 登录成功备注
            String userName = (String) LogObjectHolder.get();
            String message = StrUtil.format("【{}】登录系统", userName);
            log.info("异步线程插入登录日志....记录时间:{}", DateUtil.now());
            LogManager.me().execute(LogTaskFactory.loginSuccessLog(configurer.getRequestNo(), configurer.getRequestIp(), message));
            return result;
        } catch (Throwable throwable) {
            if (log.isDebugEnabled()) {
                log.debug("请求登录失败");
            }
            String username = (String) LogObjectHolder.get();
            String exceptMsg = ExceptionUtil.getSimpleMessage(throwable);
            String message = StrUtil.format("【{}】登录系统失败,原因:【{}】", username, exceptMsg);
            log.info("异步线程插入登录失败日志....记录时间:{}", DateUtil.now());
            LogManager.me().execute(LogTaskFactory.loginFailLog(configurer.getRequestNo(), configurer.getRequestIp(), message));
            throw throwable;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("清空临时保存器");
            }
            LogObjectHolder.remove();
        }
    }

    @Around("cutLogoutService()")
    public Object logoutAround(ProceedingJoinPoint point) throws Throwable {
        if (log.isDebugEnabled()) {
            log.debug("初始化临时保存器");
        }
        LogObjectHolder.init();
        HandleConfigurer configurer = build();
        try {
            Object result = point.proceed();
            if (log.isDebugEnabled()) {
                log.debug("请求退出登录成功");
            }
            //注销登录成功备注
            WebLoginUser webLoginUser = (WebLoginUser) LogObjectHolder.get();
            if (webLoginUser != null) {
                String message = StrUtil.format("【{}】退出系统", webLoginUser.getUserName());
                log.info("异步线程插入注销登录成功日志....记录时间:{}", DateUtil.now());
                LogManager.me().execute(LogTaskFactory.logoutSuccessLog(configurer.getRequestNo(), configurer.getRequestIp(), message));
            }
            return result;
        } catch (Throwable throwable) {
            if (log.isDebugEnabled()) {
                log.debug("请求退出登录失败");
            }
            WebLoginUser webLoginUser = (WebLoginUser) LogObjectHolder.get();
            if (webLoginUser != null) {
                String exceptMsg = ExceptionUtil.getSimpleMessage(throwable);
                String message = StrUtil.format("【{}】退出系统失败,原因:【{}】", webLoginUser.getUserName(), exceptMsg);
                log.info("异步线程插入注销登录失败日志....记录时间:{}", DateUtil.now());
                LogManager.me().execute(LogTaskFactory.logoutFailLog(configurer.getRequestNo(), configurer.getRequestIp(), message));
            }
            throw throwable;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("清空临时保存器");
            }
            LogObjectHolder.remove();
        }
    }

    @Around("cutResourceService()")
    public Object resourceAround(ProceedingJoinPoint point) throws Throwable {
        //初始化临时保存器
        if (log.isDebugEnabled()) {
            log.debug("初始化临时保存器");
        }
        //临时保存器
        LogObjectHolder.init();
        HandleConfigurer configurer = build();
        try {
            Object result = point.proceed();
            if (log.isDebugEnabled()) {
                log.debug("请求用户权限成功");
            }
            return result;
        } catch (Throwable throwable) {
            if (log.isDebugEnabled()) {
                log.debug("请求用户权限失败");
            }
            WebLoginUser webLoginUser = (WebLoginUser) LogObjectHolder.get();
            if (webLoginUser != null) {
                String exceptMsg = ExceptionUtil.getSimpleMessage(throwable);
                String message = StrUtil.format("【{}】获取权限失败,原因:【{}】", webLoginUser.getUserName(), exceptMsg);
                log.info("异步线程插入获取权限资源失败日志....记录时间:{}", DateUtil.now());
                LogManager.me().execute(LogTaskFactory.resourceLog(configurer.getRequestNo(), configurer.getRequestIp(), message));
            }
            throw throwable;
        } finally {
            if (log.isDebugEnabled()) {
                log.debug("清空临时保存器");
            }
            LogObjectHolder.remove();
        }
    }

    /**
     * 组装必要信息
     *
     * @return HandleConfigurer
     */
    private HandleConfigurer build() {
        HttpServletRequest request = HttpContextUtil.getRequest();
        HandleConfigurer configurer = new HandleConfigurer();
        // 唯一请求号
        configurer.setRequestNo(HttpContextUtil.getResponse().getHeader("Request-No"));
        // 客户端请求真实IP
        configurer.setRequestIp(HttpContextUtil.getIPAddress(request));
        return configurer;
    }
}
