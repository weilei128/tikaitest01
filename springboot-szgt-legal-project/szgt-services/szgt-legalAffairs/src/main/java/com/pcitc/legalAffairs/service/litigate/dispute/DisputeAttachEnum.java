package com.pcitc.legalAffairs.service.litigate.dispute;

/**
 * 纠纷文件所属业务
 * @author meihongli
 *
 */
public enum DisputeAttachEnum {
	
	DISPUTE("dispute_info", "纠纷附件"),
	EXECUTE("dispute_execute", "纠纷执行文件"),
	EXECUTE_PROG("dispute_execute_prog", "纠纷执行进度附件"),
	PROG("dispute_prog", "纠纷进展附件"),
	PROG_ATTACH("dispute_prog_attach", "纠纷进展相关附件"),
	SETTLED("dispute_settled", "生效判决书"),
	SETTLE_CHANGE("dispute_settle_change", "纠纷处理方式转换相关附件"),
	OTHER("dispute_other", "其他"),
	;
	
	private String type;
	private String description;
	
	private DisputeAttachEnum(String type, String description) {
		this.type = type;
		this.description = description;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDescription() {
		return description;
	}
	public void getDescription(String description) {
		this.description = description;
	}
	
}
