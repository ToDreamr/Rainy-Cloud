package com.pray.auth;

import com.pray.entity.bo.AuthUser;

/**
 * AuthUserContext
 *
 * @author 春江花朝秋月夜
 * @since 2024/4/12 21:43
 */
public class AuthUserContext {
    private static final ThreadLocal<AuthUser> AUTH_USER_CONTEXT = new ThreadLocal<>();
    public static AuthUser get(){
        return AUTH_USER_CONTEXT.get();
    }
    public static void set(AuthUser authUser) {
        AUTH_USER_CONTEXT.set(authUser);
    }

    public static void remove(){
       if (AUTH_USER_CONTEXT.get()!=null){
           AUTH_USER_CONTEXT.remove();
       }
    }
}
