package com.xuecheng.framework.domain.cms.ext;

import com.xuecheng.framework.domain.cms.CmsTemplate;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: gjh
 * @Description:   verified
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */
@Data
@ToString
public class CmsTemplateExt extends CmsTemplate {

    /**
     * 模版内容
     */
    private String templateValue;

}
