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
public class AuthToken {
    /**
     * 访问token
     */
    String access_token;
    /**
     * 刷新token
     */
    String refresh_token;
    /**
     * jwt令牌
     */
    String jwt_token;
}
