package com.xuecheng.framework.domain.course;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 课程计划-媒资
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@NoArgsConstructor
@Entity
@Table(name="teachplan_media")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class TeachplanMedia implements Serializable {
    private static final long serialVersionUID = -916357110051689485L;
    /**
     * 课程计划id
     */
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(name="teachplan_id")
    private String teachplanId;
    /**
     * 媒资文件id
     */
    @Column(name="media_id")
    private String mediaId;
    /**
     * 媒资文件的原始名称
     */
    @Column(name="media_fileoriginalname")
    private String mediaFileOriginalName;
    /**
     * 媒资文件访问地址
     */
    @Column(name="media_url")
    private String mediaUrl;
    /**
     * 课程Id
     */
    private String courseId;

}
