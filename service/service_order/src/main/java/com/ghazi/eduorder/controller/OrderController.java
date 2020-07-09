package com.ghazi.eduorder.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ghazi.commonutils.JwtUtils;
import com.ghazi.commonutils.R;
import com.ghazi.eduorder.entity.Order;
import com.ghazi.eduorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 订单 前端控制器
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/eduorder/order")
//@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    //生成订单的方法
    @PostMapping("creatOrder/{courseId}")
    public R creatOrder(@PathVariable String courseId, HttpServletRequest request){
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        String orderNo = orderService.creatOrders(courseId, memberId);
        return R.ok().data("orderId", orderNo);
    }

    @GetMapping("getOrderInfo/{orderId}")
    public R getOrderInfo(@PathVariable String orderId){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("order_no", orderId);
        Order order = orderService.getOne(wrapper);
        return R.ok().data("item", order);
    }

    //根据课程id和用户id查询订单表中订单状态
    @GetMapping("isBuyCourse/{courseId}/{memberId}")
    public boolean isBuyCourse(@PathVariable String courseId, @PathVariable(required = false, name = "memberId") String memberId){
//        System.out.println("查询成功");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id", courseId);
        wrapper.eq("member_id", memberId);
        wrapper.eq("status", 1);
        int count = orderService.count(wrapper);
        if (count>0) {
            return true;
        }else {
            return false;
        }
    }
}

