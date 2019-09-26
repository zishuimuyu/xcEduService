package com.xuecheng.manage_cms_client.dao;


import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * cms站点dao
 *
 * @author：GJH
 * @createDate：2019/9/26
 * @company：洪荒宇宙加力蹲大学
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {
    /**
     * 根据页面名称查询
     * @param siteName
     * @return
     */
    CmsSite findBySiteName(String siteName);



}
