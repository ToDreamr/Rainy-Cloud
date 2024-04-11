package com.pray.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName role
 */
@TableName(value ="role")
@Data
public class Role implements Serializable {
    /**
     *
     */
    private String time;

    /**
     *
     */
    private String roleName;

    /**
     *
     */
    private String roleType;

    /**
     *
     */
    private String roleStarLevel;

    /**
     *
     */
    private String roleCount;

    /**
     *
     */
    private String roleBottom;

    /**
     *
     */
    //设置主键id，同时设置自增
    @TableId(value = "role_id",type = IdType.AUTO)
    private String roleId;

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
        Role other = (Role) that;
        return (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getRoleName() == null ? other.getRoleName() == null : this.getRoleName().equals(other.getRoleName()))
            && (this.getRoleType() == null ? other.getRoleType() == null : this.getRoleType().equals(other.getRoleType()))
            && (this.getRoleStarLevel() == null ? other.getRoleStarLevel() == null : this.getRoleStarLevel().equals(other.getRoleStarLevel()))
            && (this.getRoleCount() == null ? other.getRoleCount() == null : this.getRoleCount().equals(other.getRoleCount()))
            && (this.getRoleBottom() == null ? other.getRoleBottom() == null : this.getRoleBottom().equals(other.getRoleBottom()))
            && (this.getRoleId() == null ? other.getRoleId() == null : this.getRoleId().equals(other.getRoleId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getRoleName() == null) ? 0 : getRoleName().hashCode());
        result = prime * result + ((getRoleType() == null) ? 0 : getRoleType().hashCode());
        result = prime * result + ((getRoleStarLevel() == null) ? 0 : getRoleStarLevel().hashCode());
        result = prime * result + ((getRoleCount() == null) ? 0 : getRoleCount().hashCode());
        result = prime * result + ((getRoleBottom() == null) ? 0 : getRoleBottom().hashCode());
        result = prime * result + ((getRoleId() == null) ? 0 : getRoleId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", time=").append(time);
        sb.append(", roleName=").append(roleName);
        sb.append(", roleType=").append(roleType);
        sb.append(", roleStarLevel=").append(roleStarLevel);
        sb.append(", roleCount=").append(roleCount);
        sb.append(", roleBottom=").append(roleBottom);
        sb.append(", roleId=").append(roleId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
