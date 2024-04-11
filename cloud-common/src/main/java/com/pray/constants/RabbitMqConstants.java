package com.pray.constants;

/**
 * RabbitMqConstants
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/30 15:45
 */
public class RabbitMqConstants {
    /**
     * 取消借阅记录时间
     */
    public static final int CANCEL_BORROW_TIME = 16;

    /**
     * 默认发送消息超时时间
     */
    public static final long TIMEOUT = 2000;

    /**
     * 消息体topic
     */
    public static final String BOOK_BORROW_TOPIC = "service-borrow";
}
