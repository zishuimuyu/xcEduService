package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * cms配置信息接口
 *
 * @author：GJH
 * @createDate：2019/9/22
 * @company：洪荒宇宙加力蹲大学
 */
@Api(value = "cms配置管理接口", description = "cms配置管理接口,提供数据类型管理/查询接口")
public interface CmsConfigControllerApi {
    /**
     * 根据id查询cms配置信息
     *
     * @param id cms配置信息ID
     * @return
     */
    @ApiOperation("根据id查询cms配置信息")
    public CmsConfig getModel(String id);
}
