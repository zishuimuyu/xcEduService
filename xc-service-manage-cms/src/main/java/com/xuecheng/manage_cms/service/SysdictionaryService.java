package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.system.SysDictionary;
import com.xuecheng.manage_cms.dao.SysDictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
@Service
public class SysdictionaryService {
    @Autowired
    SysDictionaryRepository sysDictionaryRepository;
    /**
     * 根据字典分类type查询字典信息
     *
     * @param type
     * @return
     */
    public SysDictionary findDictionaryByType(String type) {
        return sysDictionaryRepository.findBydType(type);
    }


}
