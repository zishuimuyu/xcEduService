package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsConfig;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * cms配置信息dao
 *
 * @author：GJH
 * @createDate：2019/9/26
 * @company：洪荒宇宙加力蹲大学
 */
public interface CmsConfigRepository extends MongoRepository<CmsConfig,String> {
    /**
     * 根据页面名称查询
     * @param pageName
     * @return
     */
   // CmsPage findByPageName(String pageName);

    /**
     * 根据页面名称,站点ID,页面路径查找页面
     * @param pageName
     * @param siteId
     * @param pageWebPath
     * @return
     */
  //  CmsPage findByPageNameAndSiteIdAndPageWebPath(String pageName, String siteId, String pageWebPath);

}
