package com.xuecheng.content.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xuecheng.base.exeception.XueChengPlusException;
import com.xuecheng.content.mapper.CourseTeacherMapper;
import com.xuecheng.content.mapper.TeachplanMapper;
import com.xuecheng.content.model.dto.SaveTeachplanDto;
import com.xuecheng.content.model.dto.TeachplanDto;
import com.xuecheng.content.model.po.CourseTeacher;
import com.xuecheng.content.model.po.Teachplan;
import com.xuecheng.content.service.CourseTeacherService;
import com.xuecheng.content.service.TeachplanService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 课程计划service接口实现类
 */
@Service
public class CourseTeacherServiceImpl implements CourseTeacherService{

    @Autowired
    CourseTeacherMapper courseteacherMapper;

    @Override
    public List<CourseTeacher> getCourseTeacher(Long courseId) {
        return courseteacherMapper.selectByCourseId(courseId);
    }

    @Override
    public CourseTeacher addCourseTeacher(CourseTeacher courseTeacher) {
        CourseTeacher teacher = new CourseTeacher();
        BeanUtils.copyProperties(courseTeacher, teacher);
        teacher.setCreateDate(LocalDateTime.now());
        courseteacherMapper.insert(teacher);
        return teacher;
    }

    //一改就错
    @Override
    public CourseTeacher updateCourseTeacher(CourseTeacher courseTeacher) {
        Long teacherId = courseTeacher.getId();
        CourseTeacher courseTeacher1 = courseteacherMapper.selectById(teacherId);
        if(courseTeacher1 == null){
            XueChengPlusException.cast("教师不存在");
        }

        BeanUtils.copyProperties(courseTeacher, courseTeacher1);
        courseTeacher1.setCreateDate(LocalDateTime.now());

        courseteacherMapper.deleteById(teacherId);

        int i = courseteacherMapper.insert(courseTeacher1);
        if(i < 0){
            XueChengPlusException.cast("修改课程失败");
        }
        return courseteacherMapper.selectById(teacherId);
    }

    //没通过
    @Override
    public void deleteCourseTeacher(Long courseId, Long teacherId) {
        courseteacherMapper.deleteCourseTeacher(courseId, teacherId);
    }
}

