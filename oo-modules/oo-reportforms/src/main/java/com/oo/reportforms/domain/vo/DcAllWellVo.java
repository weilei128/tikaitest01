package com.oo.reportforms.domain.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.oo.common.core.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DcAllWellVo {
    /**
     * 唯一id
     */
    @ApiModelProperty(value = "唯一id")
    private String id;

    /**
     * 井名
     */
    @ApiModelProperty(value = "井名")
    private String wellname;

//    /**
//     * 中文井名
//     */
//    @ApiModelProperty(value = "中文井名")
//    private String chinesewellname;
}
