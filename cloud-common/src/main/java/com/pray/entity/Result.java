package com.pray.entity;

import cn.hutool.json.JSONUtil;
import com.pray.utils.StringUtils;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

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

    public Result(String message) {
        this.message = message;
    }

    public static<T> Result<T> ok(T data, String message){
        return  new Result<T>(200,message,data);
    }

    public static<T> Result<T> ok(T data){
        return new Result<T>(200,data);
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
    public static <T> Result <T> fail(){return new Result<>(500);}

    public static <T> Result <T>message(int code,String message){
        return new Result<T>(code,message,null);
    }
    public static <T> Result <T> success(T data){return new Result<>(data);}
    public static <T> Result <T> success(){
        return new Result<>();
    }
    public static <T> Result <T> warn(String warnMessage){
        return new Result<>(warnMessage);
    }

    public <T> Result<T> data(T data){return new Result<>(data);}
    public  <T> Result<T> code(int code){
        return new Result<>(code);
    }

    public String jsonResult(){
        return JSONUtil.toJsonStr(this);
    }
    public boolean isSuccess() {
        return Objects.equals(200, this.code);
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    public static Result<?> convertResult(int rows) {
        return rows > 0 ? Result.success() : Result.fail();
    }
    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtils.format("redirect:{}", url);
    }

}
