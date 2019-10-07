package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.system.SysDictionary;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 数据字典API
 *
 * @author：GJH
 * @createDate：2019/10/6
 * @company：洪荒宇宙加力蹲大学
 */
@Api(value = "数据字典接口", description = "提供数据字典接口的管理、查询功能")
public interface SysDictionaryControllerApi {
    /**
     *  //根据字典分类id查询字典信息
     */
    @ApiOperation(value = "数据字典查询接口")
    public SysDictionary getByType(String type);
}
