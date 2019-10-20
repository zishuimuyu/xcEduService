package com.xuecheng.framework.domain.course.response;

import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 课程相关响应类
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class CourseResult extends ResponseResult {

    private String courseId;

    public CourseResult(ResultCode resultCode, String courseId) {
        super(resultCode);
        this.courseId = courseId;
    }

}
