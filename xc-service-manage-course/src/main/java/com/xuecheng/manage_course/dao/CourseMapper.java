package com.xuecheng.manage_course.dao;

import com.github.pagehelper.Page;
import com.xuecheng.framework.domain.course.CourseBase;
import org.apache.ibatis.annotations.Mapper;
/**
 * 课程mapper
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Mapper
public interface CourseMapper {
   /**
    * 根据id查找课程
    * @param id
    * @return
    */
   CourseBase findCourseBaseById(String id);

   /**
    * 查找多个课程(列表)
    * @return
    */
   Page<CourseBase> findCourseList();
}
