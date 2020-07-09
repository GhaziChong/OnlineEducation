package com.ghazi.staservice.client;

import com.ghazi.commonutils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-07 23:17
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //查询某一天的注册人数
    @GetMapping("/educenter/member/countRegister/{day}")
    public R countRegister(@PathVariable("day") String day);
}
