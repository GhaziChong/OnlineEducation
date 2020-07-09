package com.ghazi.staservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-07 23:00
 */
@SpringBootApplication
@MapperScan("com.ghazi.staservice.mapper")
@ComponentScan("com.ghazi")
@EnableDiscoveryClient
@EnableFeignClients
@EnableScheduling
public class StaApplication {
    public static void main(String[] args) {
        SpringApplication.run(StaApplication.class, args);
    }
}
