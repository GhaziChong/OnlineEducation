package com.ghazi.msmservice.controller;

import com.ghazi.commonutils.R;
import com.ghazi.msmservice.service.MsmService;
import com.ghazi.msmservice.utils.RandomUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-01 16:20
 */
@RestController
@RequestMapping("/edumsm/msm")
@CrossOrigin
public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    //发送短信方法
    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable String phone) {
        //从redis获取验证码，如果获取到直接发送
        String code = redisTemplate.opsForValue().get(phone);
        if (!StringUtils.isEmpty(code)){
            return R.ok();
        }
        //如果redis获取不到，进行阿里云发送

        //生成随机值，传递阿里云进行发送
        code = RandomUtil.getFourBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);

        //调用service发送短信
        boolean isSend = msmService.send(param, phone);
        if (isSend) {
            //将验证码添加到redis中，并设置有效时间为5分钟
            redisTemplate.opsForValue().set(phone, code, 5, TimeUnit.MINUTES);
            return R.ok();
        } else {
            return R.error().message("短信发送失败");
        }
    }
}
