package com.xuecheng.manage_cms.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.manage_cms.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * CMS前端控制器
 * @author GJH
 * @version 1.0
 * @create 2018-09-12 17:24
 **/
@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    PageService pageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {

        //调用service
        return pageService.findList(page, size, queryPageRequest);
    }

    /**
     * 新增页面
     *
     * @param cmsPage
     * @return
     */
    @Override
    @PostMapping("/add")
    public CmsPageResult add(@RequestBody  CmsPage cmsPage) {
        return pageService.add(cmsPage);
    }

    /**
     * 根据ID删除页面
     *
     * @param id
     * @return
     */
    @Override
    @DeleteMapping("/del/{id}")
    public ResponseResult deleteById(@PathVariable("id") String id) {
        return pageService.deleteById(id);
    }

    /**
     * 根据ID查找页面
     *
     * @param id
     * @return
     */
    @Override
    @GetMapping("/get/{id}")
    public CmsPage findById(@PathVariable("id") String id) {
        return pageService.getCmsPageById(id);
    }

    /**
     *  修改页面
     * @param id
     * @param cmsPage
     * @return
     */
    @Override
    @PutMapping("/edit/{id}")
    public CmsPageResult edit(@PathVariable("id")String id,@RequestBody CmsPage cmsPage) {
        return pageService.update(id,cmsPage);
    }
}
