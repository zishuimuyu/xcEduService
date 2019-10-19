package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import org.apache.ibatis.annotations.Mapper;

/**
 * 课程分类mapper
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
@Mapper
public interface CategoryMapper {
    /**
     * 查询课程分类列表
     */
    public CategoryNode selectList();
}

