package com.pray.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncMsgPost
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/18 12:33
 */
@Service
@Slf4j
public class AsyncMsgPost {
    @Async()
    public void postMsgByAsync(String msg)
    {
        log.info("通过异步注解Async实际完成发送消息:{}",msg);
    }
}
