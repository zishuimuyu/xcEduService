package com.xuecheng.manage_cms.dao;


import com.xuecheng.framework.domain.cms.CmsTemplate;
import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * cms模版dao
 *
 * @author：GJH
 * @createDate：2019/9/26
 * @company：洪荒宇宙加力蹲大学
 */
public interface CmsTemplateRepository extends MongoRepository<CmsTemplate,String> {


}
