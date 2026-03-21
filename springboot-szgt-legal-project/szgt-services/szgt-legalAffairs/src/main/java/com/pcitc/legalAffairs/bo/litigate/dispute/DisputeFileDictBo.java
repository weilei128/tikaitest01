package com.pcitc.legalAffairs.bo.litigate.dispute;

import lombok.Data;

@Data
public class DisputeFileDictBo {

    private Long fId;

    /**
     * 目录ID
     */
    
    private Long fkDictId;

    /**
     * 目录名称
     */
    private String fkDictName;

    /**
     * 文件ID
     */
    private Long fkFileId;

    /**
     * 文件名
     */
    private String fkFileName;

    /**
     * 文件路径
     */
    private String fkFilePath;

    /**
     * 文件扩展名
     */
    private String fkFileExt;

    /**
     * 纠纷ID
     */
    private Long fkDisputeId;

    /**
     * 纠纷名
     */
    private String fkDisputeName;

    /**
     * 文件类型
     */
    private String fFileType;

    /**
     * 业务ID
     */
    private Long fBusinessId;

}