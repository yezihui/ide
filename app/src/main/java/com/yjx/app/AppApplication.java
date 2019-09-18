package com.yjx.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 
 * <p> 
 * app端服务启动入口
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/17 16:20
 */
@Slf4j
@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) {
        log.info("APP接口服务启动成功");
    }

}
