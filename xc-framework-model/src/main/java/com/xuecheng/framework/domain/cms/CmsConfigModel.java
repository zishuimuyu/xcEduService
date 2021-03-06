package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class CmsConfigModel {
    private String key;
    private String name;
    private String url;
    private Map mapValue;
    private String value;

}
