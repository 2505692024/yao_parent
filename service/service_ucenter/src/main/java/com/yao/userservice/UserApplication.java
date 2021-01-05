package com.yao.userservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yaoheng
 * @date 2021/1/4 18:20
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yao"})
@EnableDiscoveryClient
@MapperScan("com.yao.userservice.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
