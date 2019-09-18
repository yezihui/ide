package com.yjx.mongo.config;

import com.yjx.mongo.modular.base.service.TestService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 * 默认mongo配置
 * </p>
 *
 * @author Shawn Deng
 * @date 2019-05-07 19:23
 */
@Configuration
@EnableAsync
@EnableMongoRepositories(basePackages = {"com.yjx.mongo.modular"})
public class DefaultMongoAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public TestService testService() {
        return new TestService();
    }
}
