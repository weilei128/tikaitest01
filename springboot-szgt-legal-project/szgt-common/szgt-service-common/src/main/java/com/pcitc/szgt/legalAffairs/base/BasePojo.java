package com.pcitc.szgt.legalAffairs.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;

import java.util.Date;

/***
 * @description 基类pojo
 * @author leigang
 * @date 2020年2月11日 17:20:30
 *
 */
public class BasePojo {


    @TableField(fill = FieldFill.INSERT)
    private String fCreateuser;
    @TableField(fill = FieldFill.INSERT)
    private String fCreatename;
    @TableField(fill = FieldFill.INSERT)
    private Date fCreatetime;
    @TableField(fill = FieldFill.UPDATE)
    private String fUpdateuser;
    @TableField(fill = FieldFill.UPDATE)
    private String fUpdatename;
    @TableField(fill = FieldFill.UPDATE)
    private Date fUpdatetime;

    private Integer fType;

    private Integer fState;

    private Integer fSort;

    @TableLogic
    private Integer fIsdel;

    public String getfCreateuser() {
        return fCreateuser;
    }

    public void setfCreateuser(String fCreateuser) {
        this.fCreateuser = fCreateuser;
    }

    public String getfCreatename() {
        return fCreatename;
    }

    public void setfCreatename(String fCreatename) {
        this.fCreatename = fCreatename;
    }

    public Date getfCreatetime() {
        return fCreatetime;
    }

    public void setfCreatetime(Date fCreatetime) {
        this.fCreatetime = fCreatetime;
    }

    public String getfUpdateuser() {
        return fUpdateuser;
    }

    public void setfUpdateuser(String fUpdateuser) {
        this.fUpdateuser = fUpdateuser;
    }

    public Date getfUpdatetime() {
        return fUpdatetime;
    }

    public void setfUpdatetime(Date fUpdatetime) {
        this.fUpdatetime = fUpdatetime;
    }

    public String getfUpdatename() {
        return fUpdatename;
    }

    public void setfUpdatename(String fUpdatename) {
        this.fUpdatename = fUpdatename;
    }

    public Integer getfType() {
        return fType;
    }

    public void setfType(Integer fType) {
        this.fType = fType;
    }

    public Integer getfState() {
        return fState;
    }

    public void setfState(Integer fState) {
        this.fState = fState;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }
}
