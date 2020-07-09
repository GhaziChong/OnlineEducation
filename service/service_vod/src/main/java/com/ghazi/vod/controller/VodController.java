package com.ghazi.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.ghazi.commonutils.R;
import com.ghazi.servicebase.exceptionhandler.GuliException;
import com.ghazi.vod.service.VodService;
import com.ghazi.vod.utils.ConstantVodUtils;
import com.ghazi.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.HeaderParam;
import java.util.List;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-06-27 22:18
 */
@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;

    //上传视频到阿里云
    @PostMapping("uploadAliyunVideo")
    public R uploadAliyunVideo(MultipartFile file){
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId", videoId);
    }

    @DeleteMapping("removeAliyunVideo/{id}")
    public R removeAliyunVideo(@PathVariable String id){
        try {
            //初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建request对象
            DeleteVideoRequest request = new DeleteVideoRequest();
            //向request中设置视频id
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        }catch (Exception e){
            e.printStackTrace();
            throw new GuliException(20001, "删除视频失败");
        }
    }

    //删除多个阿里云视频
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoList") List videoIdList){
        vodService.removeMoreAliyunVideo(videoIdList);
        return R.ok();
    }

    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth (@PathVariable String id) {
        try {

            //创建初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);

            //创建获取凭证request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();

            //像request设置视频id
            request.setVideoId(id);

            //调用方法得到凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();
            return R.ok().data("playAuth", playAuth);

        } catch (Exception e) {
            throw new GuliException(20001, "获取凭证失败");
        }
    }
}
