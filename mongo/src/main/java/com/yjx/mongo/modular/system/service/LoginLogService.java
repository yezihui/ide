package com.yjx.mongo.modular.system.service;

import com.yjx.mongo.document.system.LoginLog;
import com.yjx.mongo.modular.system.repository.LoginLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/12/4 11:51
 */
public class LoginLogService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private LoginLogRepository loginLogRepository;

    public void insert(LoginLog loginLog) {
        loginLogRepository.save(loginLog);
    }

}
