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
@NoArgsConstructor
@Entity
@Table(name="category")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
//@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Category implements Serializable {
    private static final long serialVersionUID = -906357110051689484L;
    @Id
    @GeneratedValue(generator = "jpa-assigned")
    @Column(length = 32)
    private String id;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 分类标签默认和名称一样
     */
    private String label;
    /**
     * 父结点id
     */
    private String parentid;
    /**
     * 是否显示
     */
    private String isshow;
    /**
     * 排序字段
     */
    private Integer orderby;
    /**
     * 是否叶子
     */
    private String isleaf;

}
