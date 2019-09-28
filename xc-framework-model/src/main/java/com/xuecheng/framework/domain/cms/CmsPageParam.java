package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
public class CmsPageParam {
    /**
     * 参数名称
     */
    private String pageParamName;
    /**
     * 参数值
     */
    private String pageParamValue;

}
