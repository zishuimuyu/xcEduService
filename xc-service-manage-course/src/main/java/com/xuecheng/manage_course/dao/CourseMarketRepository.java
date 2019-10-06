package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.CourseMarket;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 课程营销管理dao
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
public interface CourseMarketRepository extends JpaRepository<CourseMarket,String> {
}
