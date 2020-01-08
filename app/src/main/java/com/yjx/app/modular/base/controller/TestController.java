package com.yjx.app.modular.base.controller;

import com.yjx.entity.base.BaseAbilityEntity;
import com.yjx.mongo.document.system.LoginLog;
import com.yjx.mongo.modular.base.repository.TestRepository;
import com.yjx.mongo.modular.base.service.TestService;
import com.yjx.mongo.modular.system.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p> 
 * 测试控制类
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/17 16:45
 */
@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @Resource
    private TestRepository testRepository;

    @Autowired
    private LoginLogService loginLogService;

//    @Resource
//    private static LoginLogRepository loginLogRepository;

    @GetMapping("/")
    public String home() {
        return "Spring is here!";
    }

    @GetMapping("/save")
    public String test() {
        LoginLog loginLog = new LoginLog();
        loginLog.setIpAddress("192.168.1.111");
        loginLog.setLogName("login");
        loginLog.setMessage("登录");
        loginLog.setRequestNo("23");
        loginLog.setSucceed("ssss");
//        loginLogRepository.save(loginLog);
        return "success";
    }

    @GetMapping("/entity")
    public BaseAbilityEntity entity() {
        BaseAbilityEntity entity = new BaseAbilityEntity();
        entity.setId(1L);
        entity.setAbilityCode("s001");
        return entity;
    }

    @GetMapping("/insertMongo")
    public void insertMongo() {
        LoginLog loginLog = new LoginLog();
        loginLog.setIpAddress("192.168.1.111");
        loginLog.setLogName("login");
        loginLog.setMessage("登录");
        loginLog.setRequestNo("23");
        loginLog.setSucceed("ssss");
        loginLogService.insert(loginLog);
    }
}
