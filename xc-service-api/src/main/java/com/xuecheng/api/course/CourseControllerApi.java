package com.xuecheng.api.course;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import com.xuecheng.framework.domain.course.Teachplan;
import com.xuecheng.framework.domain.course.ext.CourseInfo;
import com.xuecheng.framework.domain.course.ext.TeachplanNode;
import com.xuecheng.framework.domain.course.request.CourseListRequest;
import com.xuecheng.framework.domain.course.response.CourseResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * 课程管理接口
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Api(value = "课程管理接口", description = "课程管理接口，提供课程的增、删、改、查", tags = {"课程管理"})
public interface CourseControllerApi {
    /**
     * 课程计划查询
     *
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("课程计划查询")
    public TeachplanNode findTeachPlanList(String courseId);

    /**
     * 添加课程计划
     *
     * @param teachplan 课程计划
     * @return
     */
    @ApiOperation("添加课程计划")
    public ResponseResult addTeachPlan(Teachplan teachplan);

    /**
     * 分页查询
     *
     * @param page              页码
     * @param size              每页条数
     * @param courseListRequest
     * @return
     */
    @ApiOperation("课程计划查询")
    public QueryResponseResult<CourseInfo> findTeachPlanList(int page, int size, CourseListRequest courseListRequest);

    /**
     * 添加课程基础信息
     *
     * @param courseBase
     * @return
     */
    @ApiOperation("添加课程基础信息")
    public CourseResult addCourseBase(CourseBase courseBase);

    /**
     * 根据课程id查询课程信息
     *
     * @param courseId 课程id
     * @return
     * @throws RuntimeException
     */
    @ApiOperation("获取课程基础信息")
    public CourseBase getCourseBaseById(String courseId) throws RuntimeException;

    /**
     * 更新课程信息
     *
     * @param id         课程id
     * @param courseBase 课程实体
     * @return
     */
    @ApiOperation("更新课程基础信息")
    public ResponseResult updateCourseBase(String id, CourseBase courseBase);

    /**
     * 查询课程营销信息
     *
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("获取课程营销信息")
    public CourseMarket getCourseMarketById(String courseId);

    /**
     * 更新课程营销信息
     *
     * @param id           课程id
     * @param courseMarket 课程营销实体
     * @return
     */
    @ApiOperation("更新课程营销信息")
    public ResponseResult updateCourseMarket(String id, CourseMarket courseMarket);

    /**
     * 添加课程图片
     * @param courseId 课程id
     * @param pic 图片路径
     * @return
     */
    @ApiOperation("添加课程图片")
    public ResponseResult addCoursePic(String courseId,String pic);

    /**
     * 删除课程图片
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("删除课程图片")
    public ResponseResult deleteCoursePic(String courseId);

    /**
     * 获取课程基础信息
     * @param courseId 课程id
     * @return
     */
    @ApiOperation("获取课程基础信息")
    public CoursePic findCoursePic(String courseId);

}
