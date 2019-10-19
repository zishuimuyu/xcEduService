package com.xuecheng.framework.domain.system;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 系统参数实体类
 *
 * @author：GJH
 * @createDate：2019/9/30
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "t_param")
public class Param {

    /**
     * id
     */
    private int id;
    /**
     * 参数名称
     */
    @Column(name = "param_name")
    private String paramName;
    /**
     * 参数值
     */
    @Column(name = "param_value")
    private String paramValue;
    /**
     * 选项1
     */
    private String option1;
    /**
     * 选项2
     */
    private String option2;
    /**
     * 参数描述
     */
    private String description;
    /**
     * 参数备注
     */
    private String comments;
    @Column(name = "create_time")
    private String createTime;
    @Column(name = "modify_time")
    private String modifyTime;
}
