package com.pcitc.legalAffairs.bo.punish;

import java.util.List;

public class PunishProgressBo {
	
    private Long fId;

    private Long fkPunishId;

    /**
     * 执行情况
     */
    private String fDescription;

    /**
     * 处罚类型
     */
    private String fPunishType;

    /**
     * 处理时间
     */
    private String fDate;
    
    /**
     * 文件
     */
    private List<PunishFileBo> files;

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

	public String getfDescription() {
		return fDescription;
	}

	public void setfDescription(String fDescription) {
		this.fDescription = fDescription;
	}

	public String getfPunishType() {
		return fPunishType;
	}

	public void setfPunishType(String fPunishType) {
		this.fPunishType = fPunishType;
	}

	public String getfDate() {
		return fDate;
	}

	public void setfDate(String fDate) {
		this.fDate = fDate;
	}

	public List<PunishFileBo> getFiles() {
		return files;
	}

	public void setFiles(List<PunishFileBo> files) {
		this.files = files;
	}

}
