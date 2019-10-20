package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.CourseBase;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 课程类扩展
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class CourseInfo extends CourseBase {

    /**
     * 课程图片
     */
    private String pic;

}
