package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.request.QueryPageRequest;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.response.QueryResponseResult;

/**
 * @Description：cms分页查询的API
 * @author：MLNSOFT-GJH
 * @createDate：2019/2/24
 * @company：北京木联能软件股份有限公司
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
}
