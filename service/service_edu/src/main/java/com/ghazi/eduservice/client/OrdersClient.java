package com.ghazi.eduservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-07 16:48
 */

@FeignClient("service-order")
@Component
public interface OrdersClient {

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("/eduorder/order/isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable("courseId") String courseId, @PathVariable(required = false, name = "memberId") String memberId);
}
