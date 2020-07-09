package com.ghazi.eduorder.client;

import com.ghazi.commonutils.CourseWebVoOrder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * guli_parent
 *
 * @author JiaqiZhang
 * @version 2020-07-06 21:19
 */
@Component
@FeignClient("service-edu")
public interface EduClient {

    //根据课程id查询课程信息
    @PostMapping("/eduservice/coursefront/getCourseInfoOrder/{id}")
    CourseWebVoOrder getCourseInfoOrder(@PathVariable("id") String id);

}
