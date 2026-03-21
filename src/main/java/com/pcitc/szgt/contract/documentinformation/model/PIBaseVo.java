package com.pcitc.szgt.contract.documentinformation.model;

import lombok.Data;

@Data
public class PIBaseVo {
    /**
     * 传输号(uuid)
     */
    private String transmissionNo;

    /**
     * 时间戳(yyyy-MM-dd HH:mm:ss sss)
     */
    private String timeStamp;
}
