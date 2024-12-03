package com.xuecheng.content.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.content.model.po.CourseTeacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 课程-教师关系表 Mapper 接口
 * </p>
 *
 * @author itcast
 */
@Mapper
public interface CourseTeacherMapper extends BaseMapper<CourseTeacher> {

    public List<CourseTeacher> selectByCourseId(Long courseId);

    @Delete("delete from yfr_content.course_teacher where id = #{teacherId} and course_id = #{courseId}")
    void deleteCourseTeacher(Long courseId, Long teacherId);


}
