package com.xuecheng.framework.domain.filesystem.response;

import com.xuecheng.framework.domain.filesystem.FileSystem;
import com.xuecheng.framework.model.response.ResponseResult;
import com.xuecheng.framework.model.response.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传结果接受类
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class UploadFileResult extends ResponseResult{
    @ApiModelProperty(value = "文件信息", example = "true", required = true)
    FileSystem fileSystem;
    public UploadFileResult(ResultCode resultCode, FileSystem fileSystem) {
        super(resultCode);
        this.fileSystem = fileSystem;
    }

}
