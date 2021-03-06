package com.xuecheng.framework.domain.ucenter.request;

import com.xuecheng.framework.model.request.RequestData;
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
public class LoginRequest extends RequestData {

    String username;
    String password;
    String verifycode;

}
