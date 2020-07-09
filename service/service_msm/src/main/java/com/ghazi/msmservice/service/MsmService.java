package com.ghazi.msmservice.service;

import java.util.Map; /**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-01 16:21
 */
public interface MsmService {
    //发送短信方法
    boolean send(Map<String, Object> param, String phone);
}
