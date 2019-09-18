package com.yjx.app.modular.base.controller;

import com.yjx.entity.base.BaseAbilityEntity;
import com.yjx.mongo.document.base.Test;
import com.yjx.mongo.modular.base.repository.TestRepository;
import com.yjx.mongo.modular.base.service.TestService;
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

    @GetMapping("/")
    public String home() {
        return "Spring is here!";
    }

    @GetMapping("/save")
    public String test() {
        Test test = Test.builder()
                .createTime(System.currentTimeMillis())
                .Id("id3")
                .userId(3L)
                .userName("name3")
                .userTypeCode(3)
                .userTypeName("type3").build();
        testRepository.save(test);
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
        testService.insert();
        testService.update();
    }
}
