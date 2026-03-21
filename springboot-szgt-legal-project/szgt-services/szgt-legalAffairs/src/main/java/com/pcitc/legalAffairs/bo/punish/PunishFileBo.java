package com.pcitc.legalAffairs.bo.punish;

public class PunishFileBo {
	
    private Long fId;

    /**
     * 处罚单ID
     */
    private Long fkPunishId;

    /**
     * 业务ID
     */
    private Long fkBusinessId;

    /**
     * 业务代码
     */
    private String fBusinessCode;

    /**
     * 文件ID
     */
    private String fFileId;

    /**
     * 文件名
     */
    private String fFileName;

    /**
     * 文件扩展名
     */
    private String fFileExt;

    /**
     * 文件路径
     */
    private String fFilePath;

	public Long getfId() {
		return fId;
	}

	public void setfId(Long fId) {
		this.fId = fId;
	}

	public Long getFkPunishId() {
		return fkPunishId;
	}

	public void setFkPunishId(Long fkPunishId) {
		this.fkPunishId = fkPunishId;
	}

	public Long getFkBusinessId() {
		return fkBusinessId;
	}

	public void setFkBusinessId(Long fkBusinessId) {
		this.fkBusinessId = fkBusinessId;
	}

	public String getfBusinessCode() {
		return fBusinessCode;
	}

	public void setfBusinessCode(String fBusinessCode) {
		this.fBusinessCode = fBusinessCode;
	}

	public String getfFileId() {
		return fFileId;
	}

	public void setfFileId(String fFileId) {
		this.fFileId = fFileId;
	}

	public String getfFileName() {
		return fFileName;
	}

	public void setfFileName(String fFileName) {
		this.fFileName = fFileName;
	}

	public String getfFileExt() {
		return fFileExt;
	}

	public void setfFileExt(String fFileExt) {
		this.fFileExt = fFileExt;
	}

	public String getfFilePath() {
		return fFilePath;
	}

	public void setfFilePath(String fFilePath) {
		this.fFilePath = fFilePath;
	}
    
}
