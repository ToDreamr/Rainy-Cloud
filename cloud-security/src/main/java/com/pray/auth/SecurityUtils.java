package com.pray.auth;

import com.pray.entity.auth.AuthUser;
import org.springframework.stereotype.Component;

/**
 * SecurityUtils
 *
 * @author Cotton Eye Joe
 * @since 2024/9/17 15:52
 */
@Component
public class SecurityUtils {
   public static long getUserId(){
      return AuthUserContext.get().getUserId();
   }

    public static AuthUser getLoginUser() {
        return AuthUserContext.get();
    }

    //todo 加密
    public static String encryptPassword(String password) {
        return password;
    }

    public static boolean matchesPassword(String oldPassword, String password) {
        return oldPassword.equals(password);
    }
}
