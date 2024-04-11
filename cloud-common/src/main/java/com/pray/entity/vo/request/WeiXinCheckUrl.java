package com.pray.entity.vo.request;

import lombok.Data;

/**
 * <p>
 * WeiXinCheckUrl
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
