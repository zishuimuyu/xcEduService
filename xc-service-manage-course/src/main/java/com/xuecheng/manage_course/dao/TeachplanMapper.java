package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程计划mapper
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Mapper
public interface TeachplanMapper {
    /**
     * 查询课程计划信息
     * @param courseId
     * @return
     */
    public TeachplanNode selectList(String courseId);
}
