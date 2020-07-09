package com.ghazi.eduorder.client;

import com.ghazi.commonutils.UcenterMemberOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-06 21:19
 */
@Component
@FeignClient("service-ucenter")
public interface UcenterClient {
    //根据用户id获取用户信息
    @PostMapping("/educenter/member/getUserInfoOrder/{id}")
    UcenterMemberOrder getUserInfoOrder(@PathVariable("id") String id);
}
