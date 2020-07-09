package com.ghazi.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ghazi.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ghazi.eduservice.entity.frontvo.CourseFrontVo;
import com.ghazi.eduservice.entity.frontvo.CourseWebVo;
import com.ghazi.eduservice.entity.vo.CourseInfoVo;
import com.ghazi.eduservice.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author JiaqiZhang
 * @since 2020-06-22
 */
public interface EduCourseService extends IService<EduCourse> {

    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //根据课程id查询课程确认信息
    CoursePublishVo publishCourseInfo(String id);

    //删除课程
    void removeCourse(String courseId);


    Map<String,Object> getCourseFrontList(Page<EduCourse> pageCourse, CourseFrontVo courseFrontVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
