package com.pray.exception;

/**
 * CloudServiceException
 *
 * @author 春江花朝秋月夜
 * @since 2024/3/31 19:37
 */
public class CloudServiceException extends RuntimeException{
    private int code;
    private Object  object;
    public CloudServiceException(String msg){
        super(msg);
    }
    public CloudServiceException(String msg,Object object){
        super(msg);
        this.object=object;
    }
    public CloudServiceException(String msg,int code){
        super(msg);
        this.code=code;
    }

    public int getCode() {
        return code;
    }

    public Object getObject() {
        return object;
    }
}
