package com.xuecheng.framework.domain.ucenter;

import lombok.Data;
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
@Entity
@Table(name="xc_teacher")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class XcTeacher implements Serializable {
    private static final long serialVersionUID = -916357110051689786L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    private String name;
    private String pic;
    private String intro;
    private String resume;
    @Column(name="user_id")
    private String userId;

}
