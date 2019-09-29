package com.xuecheng.framework.domain.system;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * 系统参数实体类
 *
 * @author：GJH
 * @createDate：2019/9/30
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@Entity
@Table(name="t_param")
@GenericGenerator(name = "jpa-assigned", strategy = "assigned")
public class Param {
}
