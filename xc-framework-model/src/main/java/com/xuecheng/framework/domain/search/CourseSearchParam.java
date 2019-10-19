package com.xuecheng.framework.domain.search;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class CourseSearchParam implements Serializable {
    /**
     * 关键字
     */
    String keyword;
    /**
     * 一级分类
     */
    String mt;
    /**
     * 二级分类
     */
    String st;

    /**
     * 难度等级
     */
    String grade;

    /**
     * 最低价格
     */
    Float price_min;
    /**
     * 最高价格
     */
    Float price_max;

    /**
     * 排序字段
     */
    String sort;
    /**
     * 过虑字段
     */
    String filter;

}
