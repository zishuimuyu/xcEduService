package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 课程计划dao(SpringBoot Jpa)
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
public interface TeachplanRepository extends JpaRepository<Teachplan,String> {
    /**
     * 根据课程id和parentId查找节点
     * @param courseId
     * @param parentId
     * @return
     */
    List<Teachplan> findTeachplanByCourseidAndParentid(String courseId, String parentId);
}
