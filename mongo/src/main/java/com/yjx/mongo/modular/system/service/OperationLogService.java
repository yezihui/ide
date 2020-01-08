package com.yjx.mongo.modular.system.service;

import com.yjx.mongo.document.system.OperationLog;
import com.yjx.mongo.modular.system.repository.OperationLogRepository;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 *
 * @author yejx
 * @date 2019/12/4 12:00
 */
public class OperationLogService {

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private OperationLogRepository operationLogRepository;

    public void insert(OperationLog operationLog) {
        operationLogRepository.save(operationLog);
    }
}
