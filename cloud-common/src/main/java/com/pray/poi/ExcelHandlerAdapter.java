package com.pray.poi;

/**
 * Excel数据格式处理适配器
 *
 * @author 春江花朝秋月夜
 */
public interface ExcelHandlerAdapter {
    /**
     * 格式化
     *
     * @param value 单元格数据值
     * @param args  excel注解args参数组
     * @return 处理后的值
     */
    Object format(Object value, String[] args);
}
