package com.xuecheng.manage_cms.service;

import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.manage_cms.dao.CmsSiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * CMC页面配置信息服务
 * @author GJH
 * @version 1.0
 * @create 2019-09-23 18:32
 **/
@Service
public class CmsSiteService {

    @Autowired
    CmsSiteRepository cmsSiteRepository;

    /**
     * 根据ID查询CmsSite配置信息
     *
     * @param siteId 站点id
     * @return
     */
    public CmsSite findCmsSiteById(String siteId) {
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (optional.isPresent()) {
            return optional.get();
        } else {
            return null;
        }
    }

}
