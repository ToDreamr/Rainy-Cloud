package com.pray.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *
 * @TableName weapon
 */
@TableName(value ="weapon")
@Data
public class Weapon implements Serializable {
    /**
     *
     */
    private String time;

    /**
     *
     */
    private String weaponName;

    /**
     *
     */
    private String weaponType;

    /**
     *
     */
    private String weaponStartLevel;

    /**
     *
     */
    private String weaponCount;

    /**
     *
     */
    private String weaponBottom;

    /**
     *
     */
    @TableId(value = "weapon_id",type = IdType.AUTO)
    private String weaponId;

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
        Weapon other = (Weapon) that;
        return (this.getTime() == null ? other.getTime() == null : this.getTime().equals(other.getTime()))
            && (this.getWeaponName() == null ? other.getWeaponName() == null : this.getWeaponName().equals(other.getWeaponName()))
            && (this.getWeaponType() == null ? other.getWeaponType() == null : this.getWeaponType().equals(other.getWeaponType()))
            && (this.getWeaponStartLevel() == null ? other.getWeaponStartLevel() == null : this.getWeaponStartLevel().equals(other.getWeaponStartLevel()))
            && (this.getWeaponCount() == null ? other.getWeaponCount() == null : this.getWeaponCount().equals(other.getWeaponCount()))
            && (this.getWeaponBottom() == null ? other.getWeaponBottom() == null : this.getWeaponBottom().equals(other.getWeaponBottom()))
            && (this.getWeaponId() == null ? other.getWeaponId() == null : this.getWeaponId().equals(other.getWeaponId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getTime() == null) ? 0 : getTime().hashCode());
        result = prime * result + ((getWeaponName() == null) ? 0 : getWeaponName().hashCode());
        result = prime * result + ((getWeaponType() == null) ? 0 : getWeaponType().hashCode());
        result = prime * result + ((getWeaponStartLevel() == null) ? 0 : getWeaponStartLevel().hashCode());
        result = prime * result + ((getWeaponCount() == null) ? 0 : getWeaponCount().hashCode());
        result = prime * result + ((getWeaponBottom() == null) ? 0 : getWeaponBottom().hashCode());
        result = prime * result + ((getWeaponId() == null) ? 0 : getWeaponId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", time=").append(time);
        sb.append(", weaponName=").append(weaponName);
        sb.append(", weaponType=").append(weaponType);
        sb.append(", weaponStartLevel=").append(weaponStartLevel);
        sb.append(", weaponCount=").append(weaponCount);
        sb.append(", weaponBottom=").append(weaponBottom);
        sb.append(", weaponId=").append(weaponId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
