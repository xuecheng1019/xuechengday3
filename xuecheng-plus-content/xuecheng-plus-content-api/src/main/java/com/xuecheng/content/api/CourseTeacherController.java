package com.xuecheng.content.api;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.service.CourseTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 师资管理模块
 */

@RestController //@Controller  +  @ResponseBody
@Api(value = "师资管理接口", tags = "师资管理接口")
public class CourseTeacherController {

    @Autowired
    CourseTeacherService courseTeacherService;

    /**
     * 查询教师接口查询教师接口
     * @param courseId
     * @return
     */
    @ApiOperation("查询教师接口")
    @GetMapping("/courseTeacher/list/{courseId}")
    public List<CourseTeacher> getCourseTeacher(@PathVariable Long courseId){
        return courseTeacherService.getCourseTeacher(courseId);
    }

    @ApiOperation("添加教师接口")
    @PostMapping("/courseTeacher")
    public CourseTeacher addCourseTeacher(@RequestBody CourseTeacher courseTeacher){
        return courseTeacherService.addCourseTeacher(courseTeacher);
    }

    @ApiOperation("修改教师接口")
    @PutMapping("/courseTeacher")
    public CourseTeacher updateCourseTeacher(@RequestBody CourseTeacher courseTeacher){
        return courseTeacherService.updateCourseTeacher(courseTeacher);
    }

    @DeleteMapping("/courseTeacher/course/{courseId}/{teacherId}")
    public void deleteCourseTeacher(@PathVariable Long courseId, @PathVariable Long teacherId){
        courseTeacherService.deleteCourseTeacher(courseId, teacherId);
    }


}
