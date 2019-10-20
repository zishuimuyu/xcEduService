package com.xuecheng.framework.domain.course;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
@Table(name = "course_pre")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CoursePre implements Serializable {
    private static final long serialVersionUID = -916357110051689488L;
    /**
     *
     */
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    /**
     * 课程名称
     */
    private String name;
    /**
     * 适用人群
     */
    private String users;
    /**
     * 大分类
     */
    private String mt;
    /**
     * 小分类
     */
    private String st;
    /**
     * 课程等级
     */
    private String grade;
    /**
     * 学习模式
     */
    private String studymodel;
    /**
     * 课程介绍
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
     * 收费规则，对应数据字典
     */
    private String charge;
    /**
     * 有效性，对应数据字典
     */
    private String valid;
    /**
     * 咨询qq
     */
    private String qq;
    /**
     * 价格
     */
    private Float price;
    /**
     * 原价格
     */
    private Float price_old;
    /**
     * 过期时间
     */
    private Date expires;
    /**
     * 课程计划
     */
    private String teachplan;
    /**
     * 课程状态
     */
    private String status;


}
