package com.yjx.mongo.modular.base.repository;

import com.yjx.mongo.document.base.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

/** 
 * <p> 
 *  
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/17 17:38
 */
public interface TestRepository extends MongoRepository<Test, String> {
}
