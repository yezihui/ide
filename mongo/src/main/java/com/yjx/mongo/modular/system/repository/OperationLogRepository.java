package com.yjx.mongo.modular.system.repository;

import com.yjx.mongo.document.system.OperationLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>
 * 操作日志操作接口
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 16:44
 */
public interface OperationLogRepository extends MongoRepository<OperationLog, String> {

}
