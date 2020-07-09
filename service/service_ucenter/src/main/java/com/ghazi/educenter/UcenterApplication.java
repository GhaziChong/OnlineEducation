package com.ghazi.educenter;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-01 20:02
 */
@ComponentScan({"com.ghazi"})
@SpringBootApplication
@MapperScan("com.ghazi.educenter.mapper")
@EnableDiscoveryClient
public class UcenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(UcenterApplication.class, args);
    }
}
