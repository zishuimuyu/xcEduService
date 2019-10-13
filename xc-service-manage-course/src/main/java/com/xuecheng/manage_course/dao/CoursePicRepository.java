package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CoursePic;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程图片Dao
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
public interface CoursePicRepository extends JpaRepository<CoursePic,String> {
    /**
     * 删除图片,返回值是操作成功的结果记录数,
     * @param courseId
     * @return
     */
    long deleteByCourseid(String courseId);
}
