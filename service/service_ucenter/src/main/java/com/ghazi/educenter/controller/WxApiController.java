package com.ghazi.educenter.controller;

import com.ghazi.commonutils.JwtUtils;
import com.ghazi.educenter.entity.UcenterMember;
import com.ghazi.educenter.service.UcenterMemberService;
import com.ghazi.educenter.utils.ConstantWxUtils;
import com.ghazi.educenter.utils.HttpClientUtils;
import com.ghazi.servicebase.exceptionhandler.GuliException;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-04 15:50
 */
@CrossOrigin
@Controller//注意这里没有配置 @RestController
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService memberService;

    @GetMapping("callback")
    public String callback(String code, String state) {

        try{
            //得到授权临时票据code
            //System.out.println("code = " + code);
            //System.out.println("state = " + state);

            //拿着code值请求固定地址，得到access_token 和 openid
            String baseAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token" +
                    "?appid=%s" +
                    "&secret=%s" +
                    "&code=%s" +
                    "&grant_type=authorization_code";
            String accessTokenUrl = String.format(
                    baseAccessTokenUrl,
                    ConstantWxUtils.WX_OPEN_APP_ID,
                    ConstantWxUtils.WX_OPEN_APP_SECRET,
                    code
            );
            //使用httpclient发送请求，得到返回结果
            String accessTokenInfo = HttpClientUtils.get(accessTokenUrl);

            Gson gson = new Gson();
            HashMap mapAccessToken = gson.fromJson(accessTokenInfo, HashMap.class);
            String accessToken = (String) mapAccessToken.get("access_token");
            String openid = (String) mapAccessToken.get("openid");

            UcenterMember member = memberService.getOpenIdMember(openid);

            if (member == null) {

                //访问微信的资源服务器，获取用户信息
                String baseUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo" +
                        "?access_token=%s" +
                        "&openid=%s";
                String userInfoUrl = String.format(baseUserInfoUrl, accessToken, openid);

                //发送请求
                String userInfo = HttpClientUtils.get(userInfoUrl);

                //获取用户信息
                //解析json
                HashMap<String, Object> mapUserInfo = gson.fromJson(userInfo, HashMap.class);
                String nickname = (String)mapUserInfo.get("nickname");
                String headimgurl = (String)mapUserInfo.get("headimgurl");

                member = new UcenterMember();
                member.setNickname(nickname);
                member.setOpenid(openid);
                member.setAvatar(headimgurl);
                memberService.save(member);
            }

            //使用jwt根据member对象生成token对象
            String jwtToken = JwtUtils.getJwtToken(member.getId(), member.getNickname());

            //返回首页面
            return "redirect:http://localhost:3000?token="+jwtToken;


        } catch (Exception e) {
            throw new GuliException(20001, "获取用户信息失败");
        }
    }

    @GetMapping("login")
    public String genQrConnect(HttpSession session) {

        // 微信开放平台授权baseUrl
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect" +
                "?appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_login" +
                "&state=%s" +
                "#wechat_redirect";

        // 回调地址
        String redirectUrl = ConstantWxUtils.WX_OPEN_REDIRECT_URL; //获取业务服务器重定向地址
        try {
            redirectUrl = URLEncoder.encode(redirectUrl, "UTF-8"); //url编码
        } catch (UnsupportedEncodingException e) {
            throw new GuliException(20001, e.getMessage());
        }

        // 防止csrf攻击（跨站请求伪造攻击）
        //String state = UUID.randomUUID().toString().replaceAll("-", "");//一般情况下会使用一个随机数
        String state = "imhelen";//为了让大家能够使用我搭建的外网的微信回调跳转服务器，这里填写你在ngrok的前置域名

        // 采用redis等进行缓存state 使用sessionId为key 30分钟后过期，可配置
        //键："wechar-open-state-" + httpServletRequest.getSession().getId()
        //值：satte
        //过期时间：30分钟

        //生成qrcodeUrl
        String qrcodeUrl = String.format(
                baseUrl,
                ConstantWxUtils.WX_OPEN_APP_ID,
                redirectUrl,
                state);

        return "redirect:" + qrcodeUrl;
    }
}
