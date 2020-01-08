package com.yjx.mongo.modular.system.repository;

import com.yjx.mongo.document.system.LoginLog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>
 * 登录日志操作接口
 * </p>
 *
 * @author yejx
 * @date 2019/12/3 16:44
 */
public interface LoginLogRepository extends MongoRepository<LoginLog, String> {

}
