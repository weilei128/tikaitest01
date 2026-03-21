package com.pcitc.szgt.contract.make.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-27
 */
public class CrContracttext implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("TextID")
    private String TextID;

    @TableField("ContractID")
    private String ContractID;

    @TableField("TextNum")
    private String TextNum;

    @TableField("TextName")
    private String TextName;

    @TableField("TextATPath")
    private String TextATPath;

    @TableField("TextATUrl")
    private String TextATUrl;

    @TableField("MatFileNum")
    private String MatFileNum;

    @TableField("MatFileName")
    private String MatFileName;

    @TableField("MatFileATPath")
    private String MatFileATPath;

    @TableField("MatFileATUrl")
    private String MatFileATUrl;

    @TableField("Edition")
    private String Edition;

    @TableField("Issuer")
    private String Issuer;

    @TableField("Published")
    private LocalDateTime Published;

    @TableField("TextContent")
    private String TextContent;

    @TableField("TextType")
    private Integer TextType;

    @TableField("Status")
    private Integer Status;

    @TableField("LogicDel")
    private Integer LogicDel;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    @TableField("Oulabel")
    private Integer Oulabel;

    public String getTextID() {
        return TextID;
    }

    public void setTextID(String TextID) {
        this.TextID = TextID;
    }
    public String getContractID() {
        return ContractID;
    }

    public void setContractID(String ContractID) {
        this.ContractID = ContractID;
    }
    public String getTextNum() {
        return TextNum;
    }

    public void setTextNum(String TextNum) {
        this.TextNum = TextNum;
    }
    public String getTextName() {
        return TextName;
    }

    public void setTextName(String TextName) {
        this.TextName = TextName;
    }
    public String getTextATPath() {
        return TextATPath;
    }

    public void setTextATPath(String TextATPath) {
        this.TextATPath = TextATPath;
    }
    public String getTextATUrl() {
        return TextATUrl;
    }

    public void setTextATUrl(String TextATUrl) {
        this.TextATUrl = TextATUrl;
    }
    public String getMatFileNum() {
        return MatFileNum;
    }

    public void setMatFileNum(String MatFileNum) {
        this.MatFileNum = MatFileNum;
    }
    public String getMatFileName() {
        return MatFileName;
    }

    public void setMatFileName(String MatFileName) {
        this.MatFileName = MatFileName;
    }
    public String getMatFileATPath() {
        return MatFileATPath;
    }

    public void setMatFileATPath(String MatFileATPath) {
        this.MatFileATPath = MatFileATPath;
    }
    public String getMatFileATUrl() {
        return MatFileATUrl;
    }

    public void setMatFileATUrl(String MatFileATUrl) {
        this.MatFileATUrl = MatFileATUrl;
    }
    public String getEdition() {
        return Edition;
    }

    public void setEdition(String Edition) {
        this.Edition = Edition;
    }
    public String getIssuer() {
        return Issuer;
    }

    public void setIssuer(String Issuer) {
        this.Issuer = Issuer;
    }
    public LocalDateTime getPublished() {
        return Published;
    }

    public void setPublished(LocalDateTime Published) {
        this.Published = Published;
    }
    public String getTextContent() {
        return TextContent;
    }

    public void setTextContent(String TextContent) {
        this.TextContent = TextContent;
    }
    public Integer getTextType() {
        return TextType;
    }

    public void setTextType(Integer TextType) {
        this.TextType = TextType;
    }
    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status) {
        this.Status = Status;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
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
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }
    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }

    @Override
    public String toString() {
        return "CrContracttext{" +
        "TextID=" + TextID +
        ", ContractID=" + ContractID +
        ", TextNum=" + TextNum +
        ", TextName=" + TextName +
        ", TextATPath=" + TextATPath +
        ", TextATUrl=" + TextATUrl +
        ", MatFileNum=" + MatFileNum +
        ", MatFileName=" + MatFileName +
        ", MatFileATPath=" + MatFileATPath +
        ", MatFileATUrl=" + MatFileATUrl +
        ", Edition=" + Edition +
        ", Issuer=" + Issuer +
        ", Published=" + Published +
        ", TextContent=" + TextContent +
        ", TextType=" + TextType +
        ", Status=" + Status +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
