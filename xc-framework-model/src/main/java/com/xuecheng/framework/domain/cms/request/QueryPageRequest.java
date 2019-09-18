package com.xuecheng.framework.domain.cms.request;

import com.xuecheng.framework.model.request.RequestData;
import lombok.Data;

/**
 * @Description：分页查询的请求条件模型
 * @author：GJH
 * @createDate：2019/2/24
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
