package com.pcitc.szgt.contract.offeree.model;

import com.pcitc.szgt.contract.attachment.entity.SysAttachmentinfo;
import com.pcitc.szgt.contract.common.enums.offeree.OffereeTypeEnum;
import com.pcitc.szgt.contract.exception.BaseException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OffereeVo {

    //相对人基本信息
    private OffereeInfoVo offereeInfo;

    //开户行列表
    private List<OffereeBankVo> offereeBankList;

    //营业执照信息
//    private OffereeLicenseVo offereeLicense;

    //联系人信息
    private List<OffereeLinkmanVo> offereeLinkmanList;

    //附件id列表
    private List<String> attachmentIds;

    //校验数据信息
    public void validate(){

        if(offereeInfo == null){
            throw new BaseException("基本信息不完整", 500);
        }

        Integer offereetype = offereeInfo.getOffereeType();

        if (offereetype == null ||
                (offereetype != OffereeTypeEnum.Org.getType()
                        && offereetype != OffereeTypeEnum.Person.getType())){
            throw new BaseException("相对人类型值非法", 500);
        }

        if(offereetype == 0){
            //机构
            this.validateOrg();
        } else {
            //自然人
            this.validatePerson();
        }

    }

    public void validateAttachment(List<SysAttachmentinfo> attachments){

        if(attachments==null || attachments.size()==0){
            throw new BaseException("附件信息不全", 500);
        }

        if(attachments.size() != attachmentIds.size()){
            throw new BaseException("无效的附件", 500);
        }

        for(SysAttachmentinfo attachmentinfo: attachments){
            if(attachmentinfo.getPropertyID()!=null){
                throw new BaseException("无效的附件", 500);
            }
        }

        List<String> dataList = attachments.stream().map(SysAttachmentinfo::getAttachmentTypeName).collect(Collectors.toList());

        if(offereeInfo.getOffereeType() == 0){
            //机构相对人必须有`统一社会信用代码证`
            if (!dataList.contains("统一社会信用代码证")){
                throw new BaseException("附件信息不全", 500);
            }

        } else {
            //自然人相对人必须有`身份证附件`
            if (!dataList.contains("身份证附件")){
                throw new BaseException("附件信息不全", 500);
            }
        }
    }

    //如果是机构类型的相对人
    private void validateOrg(){

        //校验相对人基本信息
        if (StringUtils.isEmpty(offereeInfo.getOffereeName())) {
            throw new BaseException("相对人名称不能为空", 500);
        }

        if (StringUtils.isEmpty(offereeInfo.getOffereeCode())) {
            throw new BaseException("相对人编码为空", 500);
        }

        Integer isenable = offereeInfo.getIsEnable();
        if (isenable == null || (isenable != 1 && isenable != 0)){
            throw new BaseException("是否启用值非法", 500);
        }


        if(offereeInfo.getOffereeSort()==null){
            throw new BaseException("相对人分类不能为空", 500);
        }

//        if(offereeInfo.getOffereeeBelong() == null){
//            throw new BaseException("相对人归属不能为空", 500);
//        }
        //update 20200813机构类型非必填
       /* if(offereeInfo.getCompanyType() == null){
            throw new BaseException("机构类型不能为空", 500);
        }*/

        if(offereeInfo.getCreditCode() == null){
            throw new BaseException("统一社会信用代码不能为空", 500);
        }

        //营业执照信息 暂时不用
//        if(offereeLicense == null){
//            throw new BaseException("营业执照不完整", 500);
//        }
//
//        //校验相对人营业执照信息
//        if(offereeLicense.getBusinesslicensecode() == null){
//            throw new BaseException("注册号不能为空", 500);
//        }
//
//        if(offereeLicense.getRegisteraddr() == null){
//            throw new BaseException("注册地址不能为空", 500);
//        }
//
//        if(offereeLicense.getOfficeaddr() == null){
//            throw new BaseException("办公地址不能为空", 500);
//        }
//
//        if(offereeLicense.getFix() == null){
//            throw new BaseException("营业执照是否固定不能为空", 500);
//        }
//
//        if(offereeLicense.getFix()){
//            if(offereeLicense.getRegisterdate() == null){
//                throw new BaseException("营业期限不能为空", 500);
//            }
//        }

    }

    //如果是自然人类型的相对人
    private void validatePerson(){
        //校验自然人基本信息
        if (StringUtils.isEmpty(offereeInfo.getNaturePerson())) {
            throw new BaseException("自然人名称为空", 500);
        }

        if (StringUtils.isEmpty(offereeInfo.getIDCard())) {
            throw new BaseException("身份证号为空", 500);
        }

        if (StringUtils.isEmpty(offereeInfo.getPhone())) {
            throw new BaseException("联系电话为空", 500);
        }

        Integer isenable = offereeInfo.getIsEnable();
        if (isenable == null || (isenable != 1 && isenable != 0)){
            throw new BaseException("是否启用值非法", 500);
        }
    }

    public OffereeInfoVo getOffereeInfo() {
        return offereeInfo;
    }

    public void setOffereeInfo(OffereeInfoVo offereeInfo) {
        this.offereeInfo = offereeInfo;
    }

    public List<OffereeBankVo> getOffereeBankList() {
        return offereeBankList;
    }

    public void setOffereeBankList(List<OffereeBankVo> offereeBankList) {
        this.offereeBankList = offereeBankList;
    }

    public List<OffereeLinkmanVo> getOffereeLinkmanList() {
        return offereeLinkmanList;
    }

    public void setOffereeLinkmanList(List<OffereeLinkmanVo> offereeLinkmanList) {
        this.offereeLinkmanList = offereeLinkmanList;
    }

    public List<String> getAttachmentIds() {
        return attachmentIds;
    }

    public void setAttachmentIds(List<String> attachmentIds) {
        this.attachmentIds = attachmentIds;
    }
}
