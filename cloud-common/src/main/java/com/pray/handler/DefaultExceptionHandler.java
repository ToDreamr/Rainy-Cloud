package com.pray.handler;

import com.pray.exception.CloudServiceException;
import com.pray.utils.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * DefaultExceptionHandler
 * 自定义错误处理器，除了授权只要请求服务器成功，就返回200，错误根据错误码前端进行处理
 * @author 春江花朝秋月夜
 * @since 2024/3/31 19:48
 */
@RestController
@RestControllerAdvice
public class DefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @ExceptionHandler(CloudServiceException.class)
    public ResponseEntity<Result<Object>> cloudExceptionHandler(CloudServiceException e) {
        logger.error("cloudExceptionHandler", e);

        int responseEnum = e.getCode();
        // 失败返回失败消息 + 状态码
        if (responseEnum != HttpStatus.OK.value()) {
            return ResponseEntity.status(HttpStatus.OK).
                    body(Result.
                            fail(responseEnum, e.getMessage()));
        }
        // 失败返回消息 状态码固定为直接显示消息的状态码
        return ResponseEntity.status(HttpStatus.OK).
                body(Result.fail(e.getMessage()));
    }

}
