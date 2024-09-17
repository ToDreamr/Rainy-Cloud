package com.pray.exception.file;


import com.pray.exception.base.BaseException;

/**
 * 文件信息异常类
 *
 * @author 春江花朝秋月夜
 */
public class FileException extends BaseException {
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args) {
        super("file", code, args, null);
    }

}
