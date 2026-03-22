package com.oo.common.core.web.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 逻辑删除Entity基类
 *
 * @author
 */
public class LogicEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic(value="0", delval="2")
    @ApiModelProperty(hidden = true)
    private String delFlag;

    /**
     * 删除者
     */
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(hidden = true)
    private String delUser;

    /**
     * 删除时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(fill = FieldFill.UPDATE)
    @ApiModelProperty(hidden = true)
    private Date delTime;

    public String getDelFlag()
    {
        return delFlag;
    }

    public void setDelFlag(String searchValue)
    {
        this.delFlag = searchValue;
    }

    public String getDelUser()
    {
        return delUser;
    }

    public void setDelUser(String delUser)
    {
        this.delUser = delUser;
    }

    public Date getDelTime()
    {
        return delTime;
    }

    public void setDelTime(Date delTime)
    {
        this.delTime = delTime;
    }
}
