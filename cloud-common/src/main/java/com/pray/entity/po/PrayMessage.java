package com.pray.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公告通知
 * @TableName pray_message
 */
@TableName(value ="pray_message")
@Data
public class PrayMessage implements Serializable {
    /**
     * 管理员姓名
     */
    private String administratorName;

    /**
     * 管理员ID
     */
    @TableId(value = "administrator_id",type = IdType.AUTO)
    private Integer administratorId;


    /**
     * 公告内容
     */
    private String contents;
    /**
     * 发布时间
     */

    private LocalDateTime publishTime;

    public String getPublishTime() {
        return publishTime.toString();
    }

    public void setPublishTime(LocalDateTime publishTime) {
        this.publishTime = publishTime;
    }

    public String getExpireTime() {
        return expireTime.toString();
    }

    public void setExpireTime(LocalDateTime expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PrayMessage other = (PrayMessage) that;
        return (this.getAdministratorName() == null ? other.getAdministratorName() == null : this.getAdministratorName().equals(other.getAdministratorName()))
            && (this.getAdministratorId() == null ? other.getAdministratorId() == null : this.getAdministratorId().equals(other.getAdministratorId()))
            && (this.getContents() == null ? other.getContents() == null : this.getContents().equals(other.getContents()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAdministratorName() == null) ? 0 : getAdministratorName().hashCode());
        result = prime * result + ((getAdministratorId() == null) ? 0 : getAdministratorId().hashCode());
        result = prime * result + ((getContents() == null) ? 0 : getContents().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", administratorName=").append(administratorName);
        sb.append(", administratorId=").append(administratorId);
        sb.append(", contents=").append(contents);
        sb.append(", publishTime=").append(publishTime);
        sb.append(", expireTime=").append(expireTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
