package com.pray.utils;

import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Result
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    int code;
    String message;
    T data;

    public Result(T data) {
        this.data = data;
    }

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public static<T> Result<T> ok(T data, String message){
        return  new Result(200,message,data);
    }

    public static<T> Result<T> ok(T data){
        return new Result(200,data);
    }
    public static <T> Result<T> ok(){
        return ok(null);
    }
    public static <T> Result <T> fail(String errMessage){
        return new Result<>(500,errMessage,null);
    }
    public static <T> Result <T> fail(int code,String errMessage){
        return new Result<>(code,errMessage,null);
    }

    public static <T> Result <T>message(int code,String message){
        return new Result(code,message,null);
    }
    public <T> Result<T> data(T data){

        return new Result<>(data);
    }
    public  <T> Result<T> code(int code){
        return new Result<>(code);
    }

    public String jsonResult(){
        return JSONUtil.toJsonStr(this);
    }
}
