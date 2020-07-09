package com.ghazi.msmservice.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-21 16:50
 */

@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("${aliyun.msm.file.keyid}")
    private String keyId;

    @Value("${aliyun.msm.file.keysecret}")
    private String keySecret;


    //定义公开静态常量

    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
    }
}
