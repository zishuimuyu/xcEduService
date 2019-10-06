package com.xuecheng.manage_cms.dao;

import com.xuecheng.framework.domain.system.SysDictionary;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * TODO
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
@Repository
public interface SysDictionaryRepository extends MongoRepository<SysDictionary, String> {

    /**
     * 根据字典分类查询字典信息
     *
     * @param dType 字典类型
     * @return
     */
    SysDictionary findBydType(String dType);
}
