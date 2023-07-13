package com.logistic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;



/**
 * 表示取消数据源的自动配置
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class LogisticApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticApplication.class, args);
    }

}
