package com.ghazi.educms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-29 21:59
 */
@SpringBootApplication
@ComponentScan({"com.ghazi"}) //指定扫描位置
@MapperScan("com.ghazi.educms.mapper")
@EnableDiscoveryClient
public class CmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CmsApplication.class, args);
    }
}
