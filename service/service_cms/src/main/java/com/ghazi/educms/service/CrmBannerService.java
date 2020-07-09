package com.ghazi.educms.service;

import com.ghazi.educms.entity.CrmBanner;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-29
 */
public interface CrmBannerService extends IService<CrmBanner> {

    //查询所有banner
    List<CrmBanner> selectAllBanner();
}
