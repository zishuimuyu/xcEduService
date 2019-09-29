package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;


/**
 * @Description：课程管理前端控制器
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Api(value="课程管理接口",description = "课程管理接口，提供课程的增、删、改、查")
public interface CourseControllerApi {
    /**
     * 课程计划查询
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachplanList(String courseId);

    /**
     * 添加课程计划
     * @param teachplan 课程计划
     * @return
     */
    @ApiOperation("添加课程计划")
    public ResponseResult addTeachplan(Teachplan teachplan);

    /**
     * 分页查询
     * @param page 页码
     * @param size 每页条数
     * @param courseListRequest
     * @return
     */
    @ApiOperation("课程计划查询")
    public QueryResponseResult<CourseInfo> findTeachplanList(int page, int size, CourseListRequest courseListRequest);
}
