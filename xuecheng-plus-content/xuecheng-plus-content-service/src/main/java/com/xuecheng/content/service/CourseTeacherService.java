package com.xuecheng.content.service;

import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 课程基本信息管理业务接口
 */
public interface CourseTeacherService {
    public List<CourseTeacher> getCourseTeacher(Long courseId);

    public CourseTeacher addCourseTeacher(CourseTeacher courseTeacher);

    CourseTeacher updateCourseTeacher(CourseTeacher courseTeacher);

    void deleteCourseTeacher(Long courseId, Long teacherId);
}
