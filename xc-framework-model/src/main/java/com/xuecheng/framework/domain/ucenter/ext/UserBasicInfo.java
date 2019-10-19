package com.xuecheng.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class UserBasicInfo {

    private String id;
    private String username;
    private String userpic;
    private String name;
    private String utype;
    /**
     * 所属企业信息
     */
    private String companyId;
    /**
     * jwt令牌
     */
    private String jwt_token;

}
