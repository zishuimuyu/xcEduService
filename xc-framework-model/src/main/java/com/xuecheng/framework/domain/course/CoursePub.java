package com.xuecheng.framework.domain.course;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "course_pub")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CoursePub implements Serializable {
    private static final long serialVersionUID = -916357110051689487L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    /**
     *
     */
    private String name;
    /**
     *
     */
    private String users;
    /**
     *
     */
    private String mt;
    /**
     *
     */
    private String st;
    /**
     *
     */
    private String grade;
    /**
     *
     */
    private String studymodel;
    /**
     *
     */
    private String teachmode;
    /**
     *
     */
    private String description;
    /**
     * 图片
     */
    private String pic;
    /**
     * 时间戳
     */
    private Date timestamp;
    /**
     *
     */
    private String charge;
    /**
     *
     */
    private String valid;
    /**
     *
     */
    private String qq;
    /**
     *
     */
    private Float price;
    /**
     *
     */
    private Float price_old;
    /**
     *
     */
    private String expires;
    /**
     * 课程计划
     */
    private String teachplan;
    /**
     * 课程发布时间
     */
    @Column(name = "pub_time")
    private String pubTime;
}
