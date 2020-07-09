package com.ghazi.educenter.service;

import com.ghazi.educenter.entity.UcenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ghazi.educenter.entity.vo.RegisterVo;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-07-01
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String login(UcenterMember member);

    void register(RegisterVo registerVo);

    UcenterMember getOpenIdMember(String openid);

    //查询某一天的注册人数
    Integer countRegisterDay(String day);
}
