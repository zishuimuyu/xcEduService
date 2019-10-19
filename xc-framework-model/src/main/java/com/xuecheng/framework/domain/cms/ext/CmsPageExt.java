package com.xuecheng.framework.domain.cms.ext;

import com.xuecheng.framework.domain.cms.CmsPage;
import lombok.Data;
import lombok.ToString;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@ToString
@NoArgsConstructor
public class CmsPageExt extends CmsPage {
    private String htmlValue;

}
