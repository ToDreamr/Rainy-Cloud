package com.pray.entity.vo.request;

import lombok.Data;

/**
 * <p>
 * WeiXinCheckUrl 微信登录校验请求体
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/9/21 23:02
 */
@Data
public class WeiXinCheckUrl {
    String timestamp;
    String echostr;
    String signature;
    String nonce;
}
