package com.ghazi.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ghazi.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-16
 */
public interface EduTeacherService extends IService<EduTeacher> {

    Map<String,Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
