package com.xuecheng.framework.domain.course.ext;

import com.xuecheng.framework.domain.course.Category;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class CategoryParameter extends Category {

    /**
     * 二级分类ids
     */
    List<String> bIds;
    /**
     * 三级分类ids
     */
    List<String> cIds;

}
