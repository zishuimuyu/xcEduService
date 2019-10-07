package com.xuecheng.framework.domain.course;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@Entity
@Table(name="course_pic")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class CoursePic implements Serializable {
    private static final long serialVersionUID = -916357110051689486L;
    /**
     * 课程id
     */
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    private String courseid;
    /**
     * 图片id
     */
    private String pic;

}
