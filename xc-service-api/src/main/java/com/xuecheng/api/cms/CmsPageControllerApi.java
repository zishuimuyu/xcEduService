package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.ApiOperation;

/**
 * @Description：cms分页查询的API
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
public interface CmsPageControllerApi {
    /**
     * 分页查询
     * @param page 页码
     * @param size 页面大小
     * @param queryPageRequest 查询条件Model
     * @return
     */
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);

    /**
     * 新增页面
     * @param cmsPage
     * @return
     */
    public CmsPageResult add(CmsPage cmsPage);
    /**
     * 根据ID删除页面
     * @param id
     * @return
     */
    @ApiOperation("根据页面的查找页面")
    public ResponseResult deleteById(String id);

    /**
     * 根据ID查找页面
     * @param id
     * @return
     */
    @ApiOperation("根据页面的查找页面")
    public CmsPage findById(String id);

    /**
     * 修改页面
     * @param cmsPage 页面对象
     * @param id 页面id
     * @return
     */
    @ApiOperation("修改页面")
    public CmsPageResult edit(String id,CmsPage cmsPage);
    /**
     * 页面发布
     * @param pageId 页面id
     * @return
     */
    @ApiOperation("页面发布")
    public ResponseResult post(String pageId);

}
