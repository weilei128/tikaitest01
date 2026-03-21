package com.pcitc.szgt.contract.textmanage.model;

import com.pcitc.szgt.contract.exception.BaseException;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

public class TextEditVo {

    /**
     * 主键
     */
    private String FileTemplateID;

    /**
     * 前一版本id
     */
    private String ParentID;

    /**
     * 文本名称
     */
    private String TextName;

    /**
     * 文本使用环节,0订立 1变更
     */
    private Integer TextModelType;

    /**
     * 合同类型id
     */
    private List<String> typeTextIDs;

    /**
     * 使用范围
     */
    private String PubCorps;

    /**
     * 文本状态，默认已启用，1草稿 2待审核 3退回 4待启用 5已启用
     */
    private Integer Status;

    /**
     * 修改说明
     */
    private String ModifiedExplain;

    public String getFileTemplateID() {
        return FileTemplateID;
    }

    public void setFileTemplateID(String fileTemplateID) {
        FileTemplateID = fileTemplateID;
    }

    public String getParentID() {
        return ParentID;
    }

    public void setParentID(String parentID) {
        ParentID = parentID;
    }

    public String getPubCorps() {
        return PubCorps;
    }

    public void setPubCorps(String pubCorps) {
        PubCorps = pubCorps;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getModifiedExplain() {
        return ModifiedExplain;
    }

    public void setModifiedExplain(String modifiedExplain) {
        ModifiedExplain = modifiedExplain;
    }

    public String getTextName() {
        return TextName;
    }

    public void setTextName(String textName) {
        TextName = textName;
    }

    public Integer getTextModelType() {
        return TextModelType;
    }

    public void setTextModelType(Integer textModelType) {
        TextModelType = textModelType;
    }

    public List<String> getTypeTextIDs() {
        return typeTextIDs;
    }

    public void setTypeTextIDs(List<String> typeTextIDs) {
        this.typeTextIDs = typeTextIDs;
    }

    public void validateEdit(){

//        if(StringUtils.isEmpty(this.PubCorps)){
//            throw new BaseException("使用范围为空", 500);
//        }

        if(StringUtils.isEmpty(this.TextName)){
            throw new BaseException("文本名称为空", 500);
        }

        if(StringUtils.isEmpty(this.TextModelType)){
            throw new BaseException("文本使用环节为空", 500);
        }

        if(CollectionUtils.isEmpty(typeTextIDs)){
            throw new BaseException("合同类型id为空", 500);
        }

        if(StringUtils.isEmpty(this.ModifiedExplain)){
            throw new BaseException("修改说明为空", 500);
        }
    }


}
