package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
public class QueryPageRequest extends RequestData {
    /**
     * 站点id
     */
    private String siteId;
    /**
     * 页面ID
     */
    private String pageId;
    /**
     * 页面名称
     */
    private String pageName;
    /**
     * 别名
     */
    private String pageAliase;
    /**
     * 模版id
     */
    private String templateId;
}
