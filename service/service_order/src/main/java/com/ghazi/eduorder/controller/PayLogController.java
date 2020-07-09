package com.ghazi.eduorder.controller;


import com.ghazi.commonutils.R;
import com.ghazi.eduorder.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-07-06
 */
@RestController
@RequestMapping("/eduorder/paylog")
@CrossOrigin
public class PayLogController {

    @Autowired
    private PayLogService payLogService;

    //生成微信支付二维码接口
    @GetMapping("createNative/{orderNo}")
    public R createNative(@PathVariable String orderNo){
        Map map = payLogService.createNative(orderNo);
        System.out.println("返回二维码map集合"+map);
        return R.ok().data(map);
    }

    //查询订单支付状态
    //参数：订单号，根据订单号查询支付状态
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable String orderNo) {
        //调用查询接口
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        System.out.println("***********查询订单状态map集合"+map);
        if (map == null) {//出错
            return R.error().message("支付出错");
        }
        if (map.get("trade_state").equals("SUCCESS")) {//如果成功
            //更改订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功");
        }

        return R.ok().code(25000).message("支付中");
    }

}

