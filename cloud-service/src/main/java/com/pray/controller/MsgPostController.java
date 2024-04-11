package com.pray.controller;

import com.pray.service.AsyncMsgPost;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * MsgPostController
 *
 * @author 春江花朝秋月夜
 * @since 2024/1/18 12:39
 */
@Component
@Slf4j
public class MsgPostController {
    @Resource
    private AsyncMsgPost msgPost;
    public void post(String msg)
    {
        log.info("注册消息发送服务");
        msgPost.postMsgByAsync(msg);
        log.info("结束注册消息发送服务");
    }
}
