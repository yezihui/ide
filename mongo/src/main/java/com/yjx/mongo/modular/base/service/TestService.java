package com.yjx.mongo.modular.base.service;

import com.yjx.mongo.document.base.Test;
import com.yjx.mongo.modular.base.repository.TestRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/9/17 17:54
 */
public class TestService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private TestRepository testRepository;

    public void insert() {
        Test test = Test.builder()
                .createTime(System.currentTimeMillis())
                .Id("id2")
                .userId(2L)
                .userName("name2")
                .userTypeCode(2)
                .userTypeName("type2").build();
        testRepository.save(test);
    }

    public void update() {
        Query query = new Query((Criteria.where("userName").is("name2")));
        Update update = new Update().set("userTypeName", "nickName");
        mongoTemplate.updateFirst(query, update, Test.class);
    }
}
