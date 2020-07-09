package com.ghazi.eduorder.service;

import com.ghazi.eduorder.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 订单 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-07-06
 */
public interface OrderService extends IService<Order> {

    //生成订单
    String creatOrders(String courseId, String memberId);
}
