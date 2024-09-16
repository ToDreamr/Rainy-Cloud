package com.pray.constants;

/**
 * <p>
 * RainyConstants
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/24
 */
public class RainyConstants {
    public static final String LOGIN_USER_KEY="rainy:user:";
    public static final String USER_KEY="rainy:user:list:";
    public static final Long LOGIN_USER_TTL= 43200L;
    public static final String FLOW_LIMIT_COUNT="rainy:count:";
    public static final String FLOW_LIMIT_BLOCK="rainy:block:";

    public static final String REGISTER_USER_CODE="rainy:register:code:";
    public static final String ROLE_DEFAULT = "user";
    public static final String ROLE_ADMIN= "admin";
    public final static int ORDER_CORS = -102;
    public final static int ORDER_FLOW_LIMIT = -101;

    public final static int RECENT_DAYS=7200;
    public final static String rainy_NOTICE="rainy:notice:";
    public static final String VALID_CODE = "rainy:valid";
    public static final String REDIS_CACHE="rainy:cache:";
    public static final long NULL_TTL = 2L;
    public static final String TYPE_LOCK = "rainy:lock:";
}
