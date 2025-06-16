package com.pray.common;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * BackendLog
 *
 * @author 春江花朝秋月夜
 * @since 2023/11/2 18:41
 */
@Data
@Component
public class BackendLog {
    private String title;
    /**
     * 业务类型
     */
    private String serviceType;
    /**
     * api方法
     */
    private String method;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作人员
     */
    private String prayName;
    /**
     * 请求url
     */
    private String prayUrl;
    /**
     * 操作地址
     */
    private String prayIp;
    /**
     * 操作状态
     */
    private Integer status;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 操作时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;
}
