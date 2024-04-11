package com.pray.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName resident
 */
@TableName(value ="resident")
@Data
public class Resident implements Serializable {
    /**
     *
     */
    private String time;

    /**
     *
     */
    private String residentName;

    /**
     *
     */
    private String residentType;

    /**
     *
     */
    private String residentStarLevel;

    /**
     *
     */
    private String residentCount;

    /**
     *
     */
    private String residentBottom;

    /**
     *
     */
    @TableId(value = "resident_id",type = IdType.AUTO)
    private String residentId;

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
        Resident other = (Resident) that;
        return (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getResidentName() == null ? other.getResidentName() == null : this.getResidentName().equals(other.getResidentName()))
            && (this.getResidentType() == null ? other.getResidentType() == null : this.getResidentType().equals(other.getResidentType()))
            && (this.getResidentStarLevel() == null ? other.getResidentStarLevel() == null : this.getResidentStarLevel().equals(other.getResidentStarLevel()))
            && (this.getResidentCount() == null ? other.getResidentCount() == null : this.getResidentCount().equals(other.getResidentCount()))
            && (this.getResidentBottom() == null ? other.getResidentBottom() == null : this.getResidentBottom().equals(other.getResidentBottom()))
            && (this.getResidentId() == null ? other.getResidentId() == null : this.getResidentId().equals(other.getResidentId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getResidentName() == null) ? 0 : getResidentName().hashCode());
        result = prime * result + ((getResidentType() == null) ? 0 : getResidentType().hashCode());
        result = prime * result + ((getResidentStarLevel() == null) ? 0 : getResidentStarLevel().hashCode());
        result = prime * result + ((getResidentCount() == null) ? 0 : getResidentCount().hashCode());
        result = prime * result + ((getResidentBottom() == null) ? 0 : getResidentBottom().hashCode());
        result = prime * result + ((getResidentId() == null) ? 0 : getResidentId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", time=").append(time);
        sb.append(", residentName=").append(residentName);
        sb.append(", residentType=").append(residentType);
        sb.append(", residentStarLevel=").append(residentStarLevel);
        sb.append(", residentCount=").append(residentCount);
        sb.append(", residentBottom=").append(residentBottom);
        sb.append(", residentId=").append(residentId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
