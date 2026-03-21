package com.pcitc.szgt.contract.attachment.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-19
 */
public class SysAttachmentinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("AttachmentID")
    private String AttachmentID;

    /**
     * 业务数据主键
     */
    @TableField("PropertyID")
    private String PropertyID;

    /**
     * 附件名称，原始名称
     */
    @TableField("AttachmentName")
    private String AttachmentName;

    /**
     * 附件标题名称
     */
    @TableField("AttachmentTypeName")
    private String AttachmentTypeName;

    /**
     * 附件类型名称，主要为了区分合同哪个环节上传的附件
     */
    @TableField("AttachmentType")
    private Integer AttachmentType;

    /**
     * 附件类型编码，主要为了区分合同哪个环节上传的附件
     */
    @TableField("TypeCode")
    private Integer TypeCode;

    /**
     * 附件绝对路径
     */
    @TableField("AttachmentPath")
    private String AttachmentPath;

    /**
     * 合同环节 准备、订立、打印等
     */
    @TableField("PropertyModel")
    private String PropertyModel;

    /**
     * 合同阶段 文本审查审批、文本打印等
     */
    @TableField("Section")
    private String Section;
    /**
     * 	合同附件位置
     */
    @TableField("Location")
    private String Location ;
    
    /**
     * 附件相对路径 如http://10.238.120.135
     */
    @TableField("DocUrl")
    private String DocUrl;

    /**
     * 附件大小
     */
    @TableField("FileSize")
    private BigDecimal FileSize;

    /**
     * 附件扩展名　如 .doc,.xls
     */
    @TableField("Extension")
    private String Extension;

    /**
     * 备注
     */
    @TableField("Remark")
    private String Remark;

    /**
     * 创建人 userid
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 组织机构ID 用户所属部门
     */
    @TableField("OrgID")
    private Integer OrgID;

    /**
     * 企业标识 当前用户所在企业
     */
    @TableField("Oulabel")
    private Integer Oulabel;

    /**
     * 排序
     */
    @TableField("OrderNumber")
    private Integer OrderNumber;

    @TableField("LogicDel")
    private Integer LogicDel;

    public String getAttachmentID() {
        return AttachmentID;
    }

    public void setAttachmentID(String AttachmentID) {
        this.AttachmentID = AttachmentID;
    }
    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String PropertyID) {
        this.PropertyID = PropertyID;
    }
    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String AttachmentName) {
        this.AttachmentName = AttachmentName;
    }
    public String getAttachmentTypeName() {
        return AttachmentTypeName;
    }

    public void setAttachmentTypeName(String AttachmentTypeName) {
        this.AttachmentTypeName = AttachmentTypeName;
    }
    public Integer getAttachmentType() {
        return AttachmentType;
    }

    public void setAttachmentType(Integer AttachmentType) {
        this.AttachmentType = AttachmentType;
    }
    public Integer getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(Integer TypeCode) {
        this.TypeCode = TypeCode;
    }
    public String getAttachmentPath() {
        return AttachmentPath;
    }

    public void setAttachmentPath(String AttachmentPath) {
        this.AttachmentPath = AttachmentPath;
    }
    public String getPropertyModel() {
        return PropertyModel;
    }

    public void setPropertyModel(String PropertyModel) {
        this.PropertyModel = PropertyModel;
    }
    public String getSection() {
        return Section;
    }

    public void setSection(String Section) {
        this.Section = Section;
    }
    public String getDocUrl() {
        return DocUrl;
    }

    public void setDocUrl(String DocUrl) {
        this.DocUrl = DocUrl;
    }
    public BigDecimal getFileSize() {
        return FileSize;
    }

    public void setFileSize(BigDecimal FileSize) {
        this.FileSize = FileSize;
    }
    public String getExtension() {
        return Extension;
    }

    public void setExtension(String Extension) {
        this.Extension = Extension;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public Integer getOrgID() {
        return OrgID;
    }

    public void setOrgID(Integer OrgID) {
        this.OrgID = OrgID;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        OrderNumber = orderNumber;
    }

    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer logicDel) {
        LogicDel = logicDel;
    }

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("SysAttachmentinfo{");
        sb.append("AttachmentID='").append(AttachmentID).append('\'');
        sb.append(", PropertyID='").append(PropertyID).append('\'');
        sb.append(", AttachmentName='").append(AttachmentName).append('\'');
        sb.append(", AttachmentTypeName='").append(AttachmentTypeName).append('\'');
        sb.append(", AttachmentType=").append(AttachmentType);
        sb.append(", TypeCode=").append(TypeCode);
        sb.append(", AttachmentPath='").append(AttachmentPath).append('\'');
        sb.append(", PropertyModel='").append(PropertyModel).append('\'');
        sb.append(", Section='").append(Section).append('\'');
        sb.append(", Location='").append(Location).append('\'');
        sb.append(", DocUrl='").append(DocUrl).append('\'');
        sb.append(", FileSize=").append(FileSize);
        sb.append(", Extension='").append(Extension).append('\'');
        sb.append(", Remark='").append(Remark).append('\'');
        sb.append(", CreatedBy='").append(CreatedBy).append('\'');
        sb.append(", CreatedDate=").append(CreatedDate);
        sb.append(", OrgID=").append(OrgID);
        sb.append(", Oulabel=").append(Oulabel);
        sb.append(", OrderNumber=").append(OrderNumber);
        sb.append('}');
        return sb.toString();
    }
}
