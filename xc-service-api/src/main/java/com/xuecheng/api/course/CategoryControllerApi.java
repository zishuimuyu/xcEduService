package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.ext.CategoryNode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * TODO
 *
 * @author：GJH
 * @createDate：2019/10/5
 * @company：洪荒宇宙加力蹲大学
 */
@Api(value = "课程分类管理",description = "课程分类管理",tags = {"课程分类管理"})
public interface CategoryControllerApi {


    @ApiOperation("查询分类")
    public CategoryNode findList();
}
