package com.yjx.solr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

/** 
 * <p> 
 * 集成solr搜索引擎
 * </p> 
 *
 * @author yejx 
 * @date 2019/9/17 10:26
 */ 
@SpringBootApplication
public class SolrApplication {

    @GetMapping("/")
    public String home() {
        return "Spring is here!";
    }

    public static void main(String[] args) {
        SpringApplication.run(SolrApplication.class, args);
    }

}
