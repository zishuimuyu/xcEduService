package com.xuecheng.framework.domain.course;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 教学计划实体类
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name="teachplan")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Teachplan implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    /**
     * 课程计划id
     */
    @Id
    @GeneratedValue(generator = "jpa-uuid")
    @Column(length = 32)
    private String id;
    /**
     * 课程计划名称
     */
    private String pname;
    /**
     * 父级节点id
     */
    private String parentid;
    /**
     * 层级，分为1、2、3级
     */
    private String grade;
    /**
     * 课程类型:1视频、2文档
     */
    private String ptype;
    /**
     * 章节及课程时介绍
     */
    private String description;
    /**
     * 课程id
     */
    private String courseid;
    /**
     * 课程状态：0未发布、1已发布
     */
    private String status;
    /**
     * 排序字段
     */
    private Integer orderby;
    /**
     * 时长，单位分钟
     */
    private Double timelength;
    /**
     * 是否试学
     */
    private String trylearn;


}
