package com.pray.constants;

/**
 * <p>
 * PrayConstants
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/24
 */
public class PrayConstants {
    /**
     * 登录用户的redis key
     */
    public static final String LOGIN_USER_KEY="pray:user:";
    /**
     * 登录用户的过期时间（秒）
     */
    public static final Long LOGIN_USER_TTL= 43200L;

    public static final String FLOW_LIMIT_COUNT="pray:count:";
    public static final String FLOW_LIMIT_BLOCK="pray:block:";

    public static final String REGISTER_USER_CODE="pray:register:code:";
    public static final String ROLE_DEFAULT = "user";
    public static final String ROLE_ADMIN= "admin";
    public final static int ORDER_CORS = -102;
    public final static int ORDER_JWT = -101;

    public final static int RECENT_DAYS=3;
    public final static String PRAY_NOTICE="pray:notice:";
    public static final String VALID_CODE = "pray:valid";
    public static final String REDIS_CACHE="pray:cache:";
    public static final long NULL_TTL = 2L;
    public static final String TYPE_LOCK = "pray:lock:";
    public static final int USER_REMEMBER_SEC = 60*7;
    public static final int COMMENT_IM_MAIL_COUNT = 3;
    public static final int CODE_MAIL_COUNT = 3;
}
