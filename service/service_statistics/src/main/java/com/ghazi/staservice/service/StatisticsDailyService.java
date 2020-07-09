package com.ghazi.staservice.service;

import com.ghazi.staservice.entity.StatisticsDaily;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 网站统计日数据 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-07-07
 */
public interface StatisticsDailyService extends IService<StatisticsDaily> {

    //统计某一天的人数，生成统计数据
    void registerCount(String day);

    //显示图表
    Map<String,Object> getShowData(String type, String begin, String end);
}
