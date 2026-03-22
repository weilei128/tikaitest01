package com.oo.datamanagement.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.apache.poi.hpsf.Decimal;

import java.math.BigDecimal;
import java.util.Date;

@Data
@TableName("DC_TOOL_BORER")
public class DcToolBorer {//钻机设备库
    private static final long serialVersionUID = 1L;
    /** 唯一ID */
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;

    /** 钻机名称 */
    private String name;

    /** 设备类型 */
    private String type;

    /** 生产厂家 */
    private String factory;

    /** 型号 */
    private String model;

    /** 最大额定压力 */
    private BigDecimal maxPressure;

    /** 排序号 */
    private Integer orderNum;

    /** 创建者 */
    private String createBy;

    /** 创建时间 */
    private Date createTime;

    /** 更新者 */
    private String updateBy;

    /** 父级ID */
    private Date updateTime;

    /** 备注 */
    private String remark;

}
