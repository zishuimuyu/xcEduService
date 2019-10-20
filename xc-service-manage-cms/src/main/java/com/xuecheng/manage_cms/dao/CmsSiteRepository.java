package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * CmsSite页面dao
 *
 * @author：GJH
 * @createDate：2019/9/26
 * @company：洪荒宇宙加力蹲大学
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite,String> {


}
