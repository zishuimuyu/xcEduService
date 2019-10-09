package com.xuecheng.framework.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * TODO
 *
 * @author：GJH
 * @createDate：2019/10/9
 * @company：洪荒宇宙加力蹲大学
 */
public class BCryptUtil {
    public static String encode(String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPass = passwordEncoder.encode(password);
        return hashPass;
    }
    public static boolean matches(String password,String hashPass){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean f = passwordEncoder.matches(password, hashPass);
        return f;
    }
}
