package com.yao.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author yaoheng
 * @date 2020/12/25 11:47
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.yao"})
public class VodApplication {
    public static void main(String[] args){
        SpringApplication.run(VodApplication.class,args);
    }
}
