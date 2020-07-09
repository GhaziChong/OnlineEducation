package com.ghazi.eduservice.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ghazi.commonutils.CourseWebVoOrder;
import com.ghazi.commonutils.JwtUtils;
import com.ghazi.commonutils.R;
import com.ghazi.eduservice.client.OrdersClient;
import com.ghazi.eduservice.entity.EduCourse;
import com.ghazi.eduservice.entity.chapter.ChapterVo;
import com.ghazi.eduservice.entity.frontvo.CourseFrontVo;
import com.ghazi.eduservice.entity.frontvo.CourseWebVo;
import com.ghazi.eduservice.service.EduChapterService;
import com.ghazi.eduservice.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-05 15:47
 */
@RestController
@RequestMapping("/eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {
    @Autowired
    private EduCourseService courseService;

    @Autowired
    private EduChapterService chapterService;

    @Autowired
    private OrdersClient ordersClient;

    //条件查询带分页课程
    @PostMapping("getFrontCourseList/{page}/{limit}")
    public R getFrontCourseList(@PathVariable long page, @PathVariable long limit,
                                @RequestBody(required = false) CourseFrontVo courseFrontVo) {
        Page<EduCourse> pageCourse = new Page<>(page,limit);
        Map<String, Object> map = courseService.getCourseFrontList(pageCourse, courseFrontVo);

        return R.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId, HttpServletRequest request){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = courseService.getBaseCourseInfo(courseId);

        //根据课程id查询章节和小节信息
        List<ChapterVo> chapterVideoList = chapterService.getChapterVideoByCourseId(courseId);

        //根据课程id和用户id查询当前课程是否已经支付过了
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(memberId);
        boolean buyCourse = ordersClient.isBuyCourse(courseId, memberId);

        return R.ok().data("courseWebVo", courseWebVo).data("chapterVideoList", chapterVideoList).data("isBuy", buyCourse);
    }

    //根据课程id查询课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = courseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo, courseWebVoOrder);
        return courseWebVoOrder;
    }
}
