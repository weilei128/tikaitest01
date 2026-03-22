package com.oo.system.domain.vo;

import lombok.Data;

@Data
public class WdpQueryVo {
    private static final long serialVersionUID = 1L;

    /**
     * WDP文件的id
     */
    private String wdpId;

    /**
     * WDP文件的名称
     */
    private String wdpName;
}
