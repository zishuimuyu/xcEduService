package com.xuecheng.framework.domain.ucenter.ext;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author：GJH
 * @createDate：2019/9/28
 * @company：洪荒宇宙加力蹲大学
 */
@Data
@NoArgsConstructor
public class UserTokenStore extends AuthToken {
    /**
     * 用户id
     */
    String userId;
    /**
     * 用户类型
     */
    String utype;
    /**
     * 用户所属企业信息
     */
    String companyId;
}
