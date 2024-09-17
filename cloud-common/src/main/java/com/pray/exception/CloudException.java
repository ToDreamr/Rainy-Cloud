package com.pray.exception;

/**
 * CloudException
 *
 * @author Cotton Eye Joe
 * @since 2024/9/17 16:43
 */
public class CloudException extends CloudServiceException{
    public CloudException(String msg) {
        super(msg);
    }

    public CloudException(String msg, Object object) {
        super(msg, object);
    }

    public CloudException(String msg, int code) {
        super(msg, code);
    }
}
