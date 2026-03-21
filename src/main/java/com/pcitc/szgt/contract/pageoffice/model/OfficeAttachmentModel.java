package com.pcitc.szgt.contract.pageoffice.model;

import java.net.URLEncoder;

public class OfficeAttachmentModel {

    /**
     * 业务数据主键
     */
    private String PropertyID;

    /**
     * 附件名称，原始名称
     */
    private String AttachmentName;

    /**
     * 附件标题名称
     */
    private String AttachmentTypeName;

    /**
     * 附件类型名称，主要为了区分合同哪个环节上传的附件
     */
    private Integer AttachmentType;

    /**
     * 附件类型编码，主要为了区分合同哪个环节上传的附件
     */
    private Integer TypeCode;

    /**
     * 合同环节 准备、订立、打印等
     */
    private String PropertyModel;

    /**
     * 合同阶段 文本审查审批、文本打印等
     */
    private String Section;

    /**
     * 存储地址
     */
    private String DocUrl;

    /**
     * 附件绝对路径
     */
    private String AttachmentPath;

    /**
     * 登录凭证
     */
    private String access_token;

    /**
     * 扩展名
     */
    private String Extension;

    /**
     * 备注
     */
    private String Remark;

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String propertyID) {
        PropertyID = propertyID;
    }

    public String getAttachmentTypeName() {
        return AttachmentTypeName;
    }

    public void setAttachmentTypeName(String attachmentTypeName) {
        AttachmentTypeName = attachmentTypeName;
    }

    public Integer getAttachmentType() {
        return AttachmentType;
    }

    public void setAttachmentType(Integer attachmentType) {
        AttachmentType = attachmentType;
    }

    public Integer getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(Integer typeCode) {
        TypeCode = typeCode;
    }

    public String getPropertyModel() {
        return PropertyModel;
    }

    public void setPropertyModel(String propertyModel) {
        PropertyModel = propertyModel;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getDocUrl() {
        return DocUrl;
    }

    public void setDocUrl(String docUrl) {
        DocUrl = docUrl;
    }

    public String getAttachmentPath() {
        return AttachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        AttachmentPath = attachmentPath;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        AttachmentName = attachmentName;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OfficeAttachmentModel{");
        sb.append("PropertyID='").append(PropertyID).append('\'');
        sb.append(", AttachmentName='").append(AttachmentName).append('\'');
        sb.append(", AttachmentTypeName='").append(AttachmentTypeName).append('\'');
        sb.append(", AttachmentType=").append(AttachmentType);
        sb.append(", TypeCode=").append(TypeCode);
        sb.append(", PropertyModel='").append(PropertyModel).append('\'');
        sb.append(", Section='").append(Section).append('\'');
        sb.append(", DocUrl='").append(DocUrl).append('\'');
        sb.append(", AttachmentPath='").append(AttachmentPath).append('\'');
        sb.append(", access_token='").append(access_token).append('\'');
        sb.append(", Extension='").append(Extension).append('\'');
        sb.append(", Remark='").append(Remark).append('\'');
        sb.append('}');
        return sb.toString();
    }

    /**
     * 获取get参数字符串
     * @return
     */
    public String toParamString() {
        final StringBuilder sb = new StringBuilder("?");
        sb.append("PropertyID=").append(PropertyID==null?"":PropertyID);
        sb.append("&AttachmentTypeName=").append(AttachmentTypeName==null?"":AttachmentTypeName);
        sb.append("&AttachmentType=").append(AttachmentType==null?"":AttachmentType);
        sb.append("&TypeCode=").append(TypeCode==null?"":TypeCode);
        sb.append("&PropertyModel=").append(PropertyModel==null?"":PropertyModel);
        sb.append("&Section=").append(Section==null?"":Section);
        sb.append("&AttachmentPath=").append(AttachmentPath==null?"":AttachmentPath);
        sb.append("&access_token=").append(access_token);
        sb.append("&Extension=").append(Extension==null?"":Extension);

        try{
            sb.append("&AttachmentName=").append(AttachmentName==null?"":URLEncoder.encode(AttachmentName, "utf-8"));
            sb.append("&Remark=").append(Remark==null?"": URLEncoder.encode(Remark, "utf-8"));
        }catch (Exception e){
            e.printStackTrace();
        }

        return sb.toString();
    }
}
