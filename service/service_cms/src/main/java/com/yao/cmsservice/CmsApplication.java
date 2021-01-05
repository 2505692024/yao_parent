package com.yao.cmsservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yaoheng
 * @date 2021/1/3 11:44
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yao"})
@EnableDiscoveryClient
@MapperScan("com.yao.cmsservice.mapper")
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class,args);
    }
}
