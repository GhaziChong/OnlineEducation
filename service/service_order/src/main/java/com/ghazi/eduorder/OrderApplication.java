package com.ghazi.eduorder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-06 19:25
 */

@SpringBootApplication
@ComponentScan(basePackages = {"com.ghazi"})
@MapperScan("com.ghazi.eduorder.mapper")
@EnableDiscoveryClient
@EnableFeignClients
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
