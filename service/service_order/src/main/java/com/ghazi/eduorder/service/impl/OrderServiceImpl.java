package com.ghazi.eduorder.service.impl;

import com.ghazi.commonutils.CourseWebVoOrder;
import com.ghazi.commonutils.UcenterMemberOrder;
import com.ghazi.eduorder.client.EduClient;
import com.ghazi.eduorder.client.UcenterClient;
import com.ghazi.eduorder.entity.Order;
import com.ghazi.eduorder.mapper.OrderMapper;
import com.ghazi.eduorder.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ghazi.eduorder.utils.OrderNoUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.RunnableScheduledFuture;

/**
 * <p>
 * 订单 服务实现类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-07-06
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private EduClient eduClient;

    @Autowired
    private UcenterClient ucenterClient;

    @Override
    public String creatOrders(String courseId, String memberId) {
        //通过远程调用根据用户id获取用户信息
        UcenterMemberOrder userInfoOrder = ucenterClient.getUserInfoOrder(memberId);

        //通过远程调用根据课程id获取课程信息
        CourseWebVoOrder courseInfoOrder = eduClient.getCourseInfoOrder(courseId);

        Order order = new Order();

        order.setOrderNo(OrderNoUtil.getOrderNo());
        order.setCourseId(courseId);
        order.setCourseTitle(courseInfoOrder.getTitle());
        order.setCourseCover(courseInfoOrder.getCover());
        order.setTeacherName("test");
        order.setTotalFee(courseInfoOrder.getPrice());
        order.setMemberId(memberId);
        order.setMobile(userInfoOrder.getMobile());
        order.setNickname(userInfoOrder.getNickname());

        order.setStatus(0); //订单状态
        order.setPayType(1); //支付类型

        baseMapper.insert(order);

        return order.getOrderNo();
    }
}
