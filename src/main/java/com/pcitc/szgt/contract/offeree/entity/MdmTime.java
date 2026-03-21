package com.pcitc.szgt.contract.offeree.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-06-22
 */
public class MdmTime implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * INSYS_INUNIT 内部单位   INSYS_OUTUNIT 外部单位
     */
    @TableField("sysCode")
    private String sysCode;

    /**
     * 上次同步时间
     */
    private LocalDateTime lasttime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }
    public LocalDateTime getLasttime() {
        return lasttime;
    }

    public void setLasttime(LocalDateTime lasttime) {
        this.lasttime = lasttime;
    }

    @Override
    public String toString() {
        return "MdmTime{" +
        "id=" + id +
        ", sysCode=" + sysCode +
        ", lasttime=" + lasttime +
        "}";
    }
}
