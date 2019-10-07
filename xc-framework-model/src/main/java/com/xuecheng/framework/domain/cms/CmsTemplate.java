package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * cms页面模版类
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@Document(collection = "cms_template")
public class CmsTemplate {

    /**
     * 站点ID
     */
    private String siteId;
    /**
     * 模版ID
     */
    @Id
    private String templateId;
    /**
     * 模版名称
     */
    private String templateName;
    /**
     * 模版参数
     */
    private String templateParameter;

    /**
     * 模版文件Id
     */
    private String templateFileId;
}
