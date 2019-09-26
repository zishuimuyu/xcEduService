package com.xuecheng.manage_cms_client.service;


import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSDownloadStream;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.CmsSite;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.dao.CmsSiteRepository;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Optional;

/**
 * TODO
 * CMS页面服务类
 *
 * @author：GJH
 * @createDate：2019/9/26
 * @company：洪荒宇宙加力蹲大学
 */
@Service
public class PageService {
    private static  final Logger LOGGER = LoggerFactory.getLogger(PageService.class);

    @Autowired
    GridFsTemplate gridFsTemplate;

    @Autowired
    GridFSBucket gridFSBucket;

    @Autowired
    CmsPageRepository cmsPageRepository;

    @Autowired
    CmsSiteRepository cmsSiteRepository;
    /**
     * /将页面html保存到页面物理路径
     *
     * @param pageId
     */
    public void savePageToServerPath(String pageId) {

        //获取Html文件的id,从cmsPage中获取
        CmsPage cmsPage =this.findCmsPageById(pageId);
        if (cmsPage==null){
            ExceptionCast.cast(CmsCode.CMS_PAGE_NOTEXISTS);
        }
        //获取html页面的id
        String htmlFileId = cmsPage.getHtmlFileId();
        //从gridFS中查询Html文件
        InputStream htmlInputStream = this.getFileById(htmlFileId);
        if (htmlInputStream==null){
            LOGGER.error("getFileById InputStream is null,htmlInputStream:{}",htmlFileId);
            return;
        }
        //得到站点信息
        CmsSite cmsSite = this.findCmsSiteById(cmsPage.getSiteId());
        //得到站点的物理路径
        String sitePhysicalPath = cmsSite.getSitePhysicalPath();
        //得到页面的物理路径
        String pagePath = sitePhysicalPath+cmsPage.getPagePhysicalPath()+cmsPage.getPageName();
        //将Html文件保存到物理路径
        FileOutputStream fileOutputStream=null;
        try {
             fileOutputStream = new FileOutputStream(new File(pagePath));
            IOUtils.copy(htmlInputStream,fileOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                htmlInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据id获取cmsPage
     * @param pageId
     * @return
     */
    public CmsPage findCmsPageById(String pageId) {
        CmsPage cmsPage = null;
        Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
        if (optional.isPresent()) {
            cmsPage = optional.get();
        }
        return cmsPage;
    }

    /**
     * 根据htmlFileId从gridFS中查询Html文件
     * @param htmlFileId
     * @return
     */
    public InputStream getFileById(String htmlFileId){
        //获取文件对象
        GridFSFile gridFSFile = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(htmlFileId)));
        //获取下载流
        GridFSDownloadStream gridFSDownloadStream = gridFSBucket.openDownloadStream(gridFSFile.getObjectId());
        GridFsResource gridFsResource = new GridFsResource(gridFSFile,gridFSDownloadStream);
        try {
            return gridFsResource.getInputStream();
        } catch (IOException e) {
            ExceptionCast.cast(CmsCode.CMS_GENERATEHTML_HTMLISNULL);
        }
        return null;
    }

    /**
     * 根据站点id获取站点信息
     * @param siteId
     * @return
     */
    public CmsSite findCmsSiteById(String siteId){
        Optional<CmsSite> optional = cmsSiteRepository.findById(siteId);
        if (optional.isPresent()){
            CmsSite cmsSite = optional.get();
            return cmsSite;
        }
        return null;
    }
}
