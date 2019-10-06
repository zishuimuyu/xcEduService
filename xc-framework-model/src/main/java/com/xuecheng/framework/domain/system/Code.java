package com.xuecheng.framework.domain.system;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 系统值值实体类
 *
 * @author：GJH
 * @createDate：2019/9/30
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@Entity
@Table(name="t_code")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class Code {
    /**
     * id
     */
    @Id
    private int id;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述
     */
    private String description;
    /**
     * 值
     */
    private String value;
    /**
     * 名称
     */
    private String name;
    /**
     * 排序
     */
    @Column(name = "order_no")
    private String orderNo;
    /***
     * 备注
     */
    private String comments;
    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private String modifyTime;
    /**
     * 创建时的时间
     */
    @Column(name = "create_time")
    private String createTime;
}
