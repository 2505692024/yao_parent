package com.yao.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yaoheng
 * @date 2020/12/3 14:41
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.yao"})
public class EduApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduApplication.class,args);
    }
}
