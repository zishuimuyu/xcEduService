package com.xuecheng.manage_course.service;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import com.xuecheng.manage_course.dao.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 课程分类服务类
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 查询分类
     */
    public CategoryNode findList() {
        return categoryMapper.selectList();
    }
}
