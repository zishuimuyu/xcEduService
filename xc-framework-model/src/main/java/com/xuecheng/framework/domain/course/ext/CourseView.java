package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.CourseBase;
import com.xuecheng.framework.domain.course.CourseMarket;
import com.xuecheng.framework.domain.course.CoursePic;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * 课程数据模型查询响应结果类型
 *
 * @author：GJH
 * @createDate：2019/10/14
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@NoArgsConstructor //无参构造方法
public class CourseView implements Serializable {
    /**
     * 基础信息
     */
    CourseBase courseBase;
    /**
     * 课程营销
     */
    CourseMarket courseMarket;
    /**
     * 课程图片
     */
    CoursePic coursePic;
    /**
     * 教学计划
     */
    TeachplanNode TeachplanNode;
}
