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
    public static final String LOGIN_USER_KEY="pray:user:";
    public static final String USER_KEY="pray:user:list:";
    public static final Long LOGIN_USER_TTL= 43200L;
    public static final String FLOW_LIMIT_COUNT="pray:count:";
    public static final String FLOW_LIMIT_BLOCK="pray:block:";

    public static final String REGISTER_USER_CODE="pray:register:code:";
    public static final String ROLE_DEFAULT = "user";
    public static final String ROLE_ADMIN= "admin";
    public final static int ORDER_CORS = -102;
    public final static int ORDER_FLOW_LIMIT = -101;

    public final static int RECENT_DAYS=7200;
    public final static String PRAY_NOTICE="pray:notice:";
    public static final String VALID_CODE = "pray:valid";
    public static final String REDIS_CACHE="pray:cache:";
    public static final long NULL_TTL = 2L;
    public static final String TYPE_LOCK = "pray:lock:";
}
