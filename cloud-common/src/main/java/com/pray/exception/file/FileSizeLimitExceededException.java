package com.pray.exception.file;

/**
 * 文件名大小限制异常类
 *
 * @author 春江花朝秋月夜
 */
public class FileSizeLimitExceededException extends FileException {
    private static final long serialVersionUID = 1L;

    public FileSizeLimitExceededException(long defaultMaxSize) {
        super("upload.exceed.maxSize", new Object[]{defaultMaxSize});
    }
}
