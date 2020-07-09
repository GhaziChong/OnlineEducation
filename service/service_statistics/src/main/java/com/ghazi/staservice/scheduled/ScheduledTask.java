package com.ghazi.staservice.scheduled;

import com.ghazi.staservice.service.StatisticsDailyService;
import com.ghazi.staservice.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-08 9:25
 */
public class ScheduledTask {

    @Autowired
    private StatisticsDailyService dailyService;

    /**
     * 测试
     * 每天七点到二十三点每五秒执行一次
     */
//    @Scheduled(cron = "0/5 * * * * ?")
//    public void task1() {
//        System.out.println("*********++++++++++++*****执行了");
//    }

    /**
     * 每天凌晨1点执行定时
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void task2() {
        //获取上一天的日期
        String day = DateUtil.formatDate(DateUtil.addDays(new Date(), -1));
        dailyService.registerCount(day);

    }
}
