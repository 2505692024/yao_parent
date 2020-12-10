package com.yao.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import javax.sql.DataSource;

/**
 * @author yaoheng
 * @date 2020/12/9 10:33
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.yao"})
public class OssApplication {
    public static void main(String[] args){
        SpringApplication.run(OssApplication.class,args);
    }
}
