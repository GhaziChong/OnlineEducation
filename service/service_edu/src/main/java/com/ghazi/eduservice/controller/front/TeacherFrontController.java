package com.ghazi.eduservice.controller.front;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ghazi.commonutils.R;
import com.ghazi.eduservice.entity.EduCourse;
import com.ghazi.eduservice.entity.EduTeacher;
import com.ghazi.eduservice.service.EduCourseService;
import com.ghazi.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-05 10:04
 */
@RestController
@RequestMapping("/eduservice/teacherfront")
//@CrossOrigin
public class TeacherFrontController {
    @Autowired
    private EduTeacherService teacherService;

    @Autowired
    private EduCourseService courseService;

    //分页查询讲师的方法
    @PostMapping("getTeacherFrontList/{page}/{limit}")
    public R getTeacherFrontList(@PathVariable long page, @PathVariable long limit) {
        Page<EduTeacher> pageTeacher = new Page<>(page,limit);
        Map<String, Object> map = teacherService.getTeacherFrontList(pageTeacher);
        //返回分页所有数据
        return R.ok().data(map);
    }

    //讲师详情的功能
    @GetMapping("getTeacherFrontInfo/{teacherId}")
    public R getTeacherFrontInfo(@PathVariable String teacherId) {
        //根据讲师id查询讲师基本信息
        EduTeacher eduTeacher = teacherService.getById(teacherId);
        //根据讲师id查询讲师所讲课程信息
        QueryWrapper<EduCourse> wrapper = new QueryWrapper<>();
        wrapper.eq("teacher_id", teacherId);
        List<EduCourse> courseList = courseService.list(wrapper);
        return R.ok().data("teacher", eduTeacher).data("courseList", courseList);
    }
}
