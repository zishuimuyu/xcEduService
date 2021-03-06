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
@Table(name = "course_index")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CourseIndex implements Serializable {
    private static final long serialVersionUID = -916357110051689587L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    private String name;
    private String users;
    private String mt;
    private String st;
    private String grade;
    private String studymodel;
    private String teachmode;
    private String description;
    /**
     * 图片
     */
    private String pic;
    /**
     * 时间戳
     */
    private Date timestamp;
    private String charge;
    private String valid;
    private String qq;
    private Float price;
    private Float price_old;
    private Date expires;
    /**
     * 课程计划
     */
    private String teachplan;


}
