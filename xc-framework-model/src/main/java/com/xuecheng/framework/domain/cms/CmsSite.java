package com.xuecheng.framework.domain.cms;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

/**
 * cms站点
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
@Document(collection = "cms_site")
public class CmsSite {

    /**
     * 站点ID
     */
    @Id
    private String siteId;
    /**
     * 站点名称
     */
    private String siteName;
    /**
     * 站点名称
     */
    private String siteDomain;
    /**
     * 站点端口
     */
    private String sitePort;
    /**
     * 站点访问地址
     */
    private String siteWebPath;
    /**
     * 创建时间
     */
    private Date siteCreateTime;
    /**
     * 站点物理路径
     */
    private String sitePhysicalPath;

}
