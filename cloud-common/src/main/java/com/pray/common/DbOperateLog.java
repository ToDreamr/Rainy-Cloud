package com.pray.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * DbOperateLog
 *
 * @author Cotton Eye Joe
 * @since 2024/11/9 20:34
 */
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class DbOperateLog {
    private Long id;
    private String module;
    private String operateContent;
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    private LocalDateTime createTime;
    private Long createTimeLong;
    private String tableName;
    private String execSql;
    private String logPosition;
    private String ip;
    private String traceId;
    private String spanId;
    private String nodeId;
    private String remark;
    public DbOperateLog setId(Long id) {
        this.id = id;
        return this;
    }

    public DbOperateLog setModule(String module) {
        this.module = module;
        return this;
    }

    public DbOperateLog setOperateContent(String operateContent) {
        this.operateContent = operateContent;
        return this;
    }

    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss",
            timezone = "GMT+8"
    )
    public DbOperateLog setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
        return this;
    }

    public DbOperateLog setCreateTimeLong(Long createTimeLong) {
        this.createTimeLong = createTimeLong;
        return this;
    }

    public DbOperateLog setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public DbOperateLog setExecSql(String execSql) {
        this.execSql = execSql;
        return this;
    }

    public DbOperateLog setLogPosition(String logPosition) {
        this.logPosition = logPosition;
        return this;
    }

    public DbOperateLog setIp(String ip) {
        this.ip = ip;
        return this;
    }

    public DbOperateLog setTraceId(String traceId) {
        this.traceId = traceId;
        return this;
    }

    public DbOperateLog setSpanId(String spanId) {
        this.spanId = spanId;
        return this;
    }

    public DbOperateLog setNodeId(String nodeId) {
        this.nodeId = nodeId;
        return this;
    }

    public DbOperateLog setRemark(String remark) {
        this.remark = remark;
        return this;
    }
}
