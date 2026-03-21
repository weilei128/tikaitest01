package com.pcitc.szgt.contract.documentinformation.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pcitc.szgt.contract.appmanager.entity.AmQuerylicense;
import com.pcitc.szgt.contract.documentinformation.entity.Officialdocumentenclosures;
import com.pcitc.szgt.contract.documentinformation.entity.Officialdocumentpushscope;
import com.pcitc.szgt.contract.documentinformation.mapper.OfficialDocumentMapper;
import com.pcitc.szgt.contract.documentinformation.mapper.OfficialdocumentenclosuresMapper;
import com.pcitc.szgt.contract.documentinformation.mapper.OfficialdocumentpushscopeMapper;
import com.pcitc.szgt.contract.documentinformation.model.Callbackdocument;
import com.pcitc.szgt.contract.documentinformation.model.Callbackdocumentdetail;
import com.pcitc.szgt.contract.documentinformation.entity.Officialdocument;
import com.pcitc.szgt.contract.documentinformation.model.Officialdocumentdetailmessage;
import com.pcitc.szgt.contract.documentinformation.model.Officialdocumentmessage;
import com.pcitc.szgt.contract.documentinformation.service.IDocumentInformationService;
import com.pcitc.szgt.contract.util.DateUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import sun.security.util.Length;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class DocumentInformationServiceImpl implements IDocumentInformationService {

@Autowired
private OfficialDocumentMapper OfficialDocumentMapper;
    @Autowired
    private OfficialdocumentpushscopeMapper officialdocumentpushscopeMapper;
    @Autowired
    private OfficialdocumentenclosuresMapper officialdocumentenclosuresMapper;

    public Callbackdocumentdetail callBackDocument(Officialdocumentdetailmessage officialdocumentdetailmessage) {

        log.info("接收OA推送过来de公文信息 documentinformation/callBackDocument{}", StringUtils.isEmpty(officialdocumentdetailmessage) ? "" : JSONObject.toJSONString(officialdocumentdetailmessage));

        Callbackdocument callbackdocument=new Callbackdocument();
        callbackdocument.setTimeStamp(DateUtil.getDateFormatStr(new Date(), DateUtil.FMT_DATETIME));
        callbackdocument.setTransmissionNo(UUIDUtils.getUUID());

        Callbackdocumentdetail callbackdocumentdetail=new Callbackdocumentdetail();
        if (org.springframework.util.StringUtils.isEmpty(officialdocumentdetailmessage)) {
            callbackdocumentdetail.setSuccess("false");
            callbackdocumentdetail.setMessage("报文不能为空");
         //   callbackdocument.setBody(callbackdocumentdetail);
            return callbackdocumentdetail;
        }
        if (org.springframework.util.StringUtils.isEmpty(officialdocumentdetailmessage.getSubject())) {
            callbackdocumentdetail.setSuccess("false");
            callbackdocumentdetail.setMessage("文档标题不能为空");
          //  callbackdocument.setBody(callbackdocumentdetail);
            return callbackdocumentdetail;
        }


        try {
            callbackdocumentdetail.setSuccess(callBackDocumentSave(officialdocumentdetailmessage));

            if(callbackdocumentdetail.getSuccess().equals("true")) {
                callbackdocumentdetail.setMessage("保存公文信息成功。");
            }else {
                callbackdocumentdetail.setMessage("保存公文信息失败。");
            }
          //  callbackdocument.setBody(callbackdocumentdetail);
        } catch (Exception ex) {

            log.info("OA公文保存失败 ："+ex.toString());

            callbackdocumentdetail.setSuccess("false");
            callbackdocumentdetail.setMessage(ex.toString());
         //   callbackdocument.setBody(callbackdocumentdetail);
        }
        return callbackdocumentdetail;
    }


    public String callBackDocumentSave(Officialdocumentdetailmessage officialdocumentdetailmessage){

        log.info("OA公文保存 callBackDocumentSave{}", StringUtils.isEmpty(officialdocumentdetailmessage) ? "" : JSONObject.toJSONString(officialdocumentdetailmessage));

        String success = "false";
        Officialdocument officialdocument=new Officialdocument();
        QueryWrapper<Officialdocument> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(Officialdocument::getSubject, officialdocumentdetailmessage.getSubject());
        Officialdocument officialdocumentSelectOne = OfficialDocumentMapper.selectOne(queryWrapper);

        if (officialdocumentSelectOne == null) {
            officialdocument.setSubject(officialdocumentdetailmessage.getSubject());//主键：文档标题

            officialdocument.setCreateCode(officialdocumentdetailmessage.getCreateCode());
            officialdocument.setCreateDept(officialdocumentdetailmessage.getCreateDept());
            officialdocument.setCreateCompany(officialdocumentdetailmessage.getCreateCompany());
            if (!StringUtils.isEmpty(officialdocumentdetailmessage.getCreateTime())&&officialdocumentdetailmessage.getCreateTime().length()>=19) {
                officialdocument.setCreateTime(LocalDateTime.parse(officialdocumentdetailmessage.getCreateTime().substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            if (!StringUtils.isEmpty(officialdocumentdetailmessage.getPushTime())&&officialdocumentdetailmessage.getPushTime().length()>=19) {
                officialdocument.setPushTime(LocalDateTime.parse(officialdocumentdetailmessage.getPushTime().substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            officialdocument.setTempateName(officialdocumentdetailmessage.getTempateName());
            officialdocument.setParam1(officialdocumentdetailmessage.getParam1());
            officialdocument.setParam2(officialdocumentdetailmessage.getParam2());
            officialdocument.setParam3(officialdocumentdetailmessage.getParam3());
            officialdocument.setCreatedDate(LocalDateTime.now());


            //处理笺（PDF形式）保存
            List<Map<Object, String>> baseinfoUrl = officialdocumentdetailmessage.getBaseinfoUrl();
            if (baseinfoUrl != null && !baseinfoUrl.isEmpty()) {
                this.decoderAttach(baseinfoUrl, "baseinfoUrl", officialdocumentdetailmessage.getSubject());
            }

            //正文下载地址
            List<Map<Object, String>> contentUrl = officialdocumentdetailmessage.getContentUrl();
            if (contentUrl != null && !contentUrl.isEmpty()) {
                this.decoderAttach(contentUrl, "contentUrl", officialdocumentdetailmessage.getSubject());
            }
            //附件保存
            List<Map<Object, String>> enclosures = officialdocumentdetailmessage.getEnclosures();
            if (enclosures != null && !enclosures.isEmpty()) {
                this.decoderAttach(enclosures, "pushScope", officialdocumentdetailmessage.getSubject());
            }
            //文档推送范围
            List<Map<Object, String>> pushScope = officialdocumentdetailmessage.getPushScope();
            if (pushScope != null && !pushScope.isEmpty()) {
                this.decoder(pushScope, officialdocumentdetailmessage.getSubject());
            }

            success = OfficialDocumentMapper.insert(officialdocument) > 0 ? "true" : "false";
            return success;

        }else{
            officialdocument.setSubject(officialdocumentSelectOne.getSubject());//主键：文档标题  不变

            officialdocument.setCreateCode(officialdocumentdetailmessage.getCreateCode());
            officialdocument.setCreateDept(officialdocumentdetailmessage.getCreateDept());
            officialdocument.setCreateCompany(officialdocumentdetailmessage.getCreateCompany());
            if (!StringUtils.isEmpty(officialdocumentdetailmessage.getCreateTime())&&officialdocumentdetailmessage.getCreateTime().length()>=19) {
                officialdocument.setCreateTime(LocalDateTime.parse(officialdocumentdetailmessage.getCreateTime().substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            if (!StringUtils.isEmpty(officialdocumentdetailmessage.getPushTime())&&officialdocumentdetailmessage.getPushTime().length()>=19) {
                officialdocument.setPushTime(LocalDateTime.parse(officialdocumentdetailmessage.getPushTime().substring(0,19), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            }
            officialdocument.setTempateName(officialdocumentdetailmessage.getTempateName());
            officialdocument.setParam1(officialdocumentdetailmessage.getParam1());
            officialdocument.setParam2(officialdocumentdetailmessage.getParam2());
            officialdocument.setParam3(officialdocumentdetailmessage.getParam3());
            officialdocument.setModifiedDate(LocalDateTime.now());

            //处理笺（PDF形式）保存
            List<Map<Object, String>> baseinfoUrl = officialdocumentdetailmessage.getBaseinfoUrl();
            if (baseinfoUrl != null && !baseinfoUrl.isEmpty()) {
                this.decoderAttachForUpdate(baseinfoUrl, "baseinfoUrl", officialdocumentdetailmessage.getSubject());
            }

            //正文下载地址
            List<Map<Object, String>> contentUrl = officialdocumentdetailmessage.getContentUrl();
            if (contentUrl != null && !contentUrl.isEmpty()) {
                this.decoderAttachForUpdate(contentUrl, "contentUrl", officialdocumentdetailmessage.getSubject());
            }
            //附件保存
            List<Map<Object, String>> enclosures = officialdocumentdetailmessage.getEnclosures();
            if (enclosures != null && !enclosures.isEmpty()) {
                this.decoderAttachForUpdate(enclosures, "pushScope", officialdocumentdetailmessage.getSubject());
            }
            //文档推送范围
            List<Map<Object, String>> pushScope = officialdocumentdetailmessage.getPushScope();
            if (pushScope != null && !pushScope.isEmpty()) {
                this.decoderForUpdate(pushScope, officialdocumentdetailmessage.getSubject());
            }

            success = OfficialDocumentMapper.updateById(officialdocument) > 0 ? "true" : "false";
            return success;


        }
    }

    private void decoderAttach(List<Map<Object, String>> attamchments,String fieldname, String subject) {
        for (Map<Object, String> map : attamchments) {
            Officialdocumentenclosures officialdocumentenclosures = new Officialdocumentenclosures();
            officialdocumentenclosures.setFieldname(fieldname);
            officialdocumentenclosures.setSubject(subject);
            officialdocumentenclosures.setEnclosureUrl(map.get("enclosureUrl"));
            officialdocumentenclosures.setEnclosureName(map.get("enclosureName"));
            officialdocumentenclosuresMapper.insert(officialdocumentenclosures);

        }
    }

    private void decoder(List<Map<Object, String>> attamchments, String subject) {
        for (Map<Object, String> map : attamchments) {
            Officialdocumentpushscope officialdocumentpushscope = new Officialdocumentpushscope();
            officialdocumentpushscope.setSubject(subject);
            officialdocumentpushscope.setUserCode(map.get("userCode"));
            officialdocumentpushscope.setUserType(map.get("userType"));
            officialdocumentpushscopeMapper.insert(officialdocumentpushscope);

        }
    }

    private void decoderAttachForUpdate(List<Map<Object, String>> attamchments,String fieldname, String subject) {

        QueryWrapper<Officialdocumentenclosures> officialdocumentenclosuresqueryWrapper = new QueryWrapper<>();
        officialdocumentenclosuresqueryWrapper.lambda().eq(Officialdocumentenclosures::getSubject, subject);
        officialdocumentenclosuresqueryWrapper.lambda().eq(Officialdocumentenclosures::getFieldname, fieldname);
        officialdocumentenclosuresMapper.delete(officialdocumentenclosuresqueryWrapper);

        for (Map<Object, String> map : attamchments) {
            Officialdocumentenclosures officialdocumentenclosures = new Officialdocumentenclosures();
            officialdocumentenclosures.setFieldname(fieldname);
            officialdocumentenclosures.setSubject(subject);
            officialdocumentenclosures.setEnclosureUrl(map.get("enclosureUrl"));
            officialdocumentenclosures.setEnclosureName(map.get("enclosureName"));
            officialdocumentenclosuresMapper.insert(officialdocumentenclosures);

        }
    }

    private void decoderForUpdate(List<Map<Object, String>> attamchments, String subject) {
        QueryWrapper<Officialdocumentpushscope> officialdocumentpushscopequeryWrapper = new QueryWrapper<>();
        officialdocumentpushscopequeryWrapper.lambda().eq(Officialdocumentpushscope::getSubject, subject);
        officialdocumentpushscopeMapper.delete(officialdocumentpushscopequeryWrapper);

        for (Map<Object, String> map : attamchments) {
            Officialdocumentpushscope officialdocumentpushscope = new Officialdocumentpushscope();
            officialdocumentpushscope.setSubject(subject);
            officialdocumentpushscope.setUserCode(map.get("userCode"));
            officialdocumentpushscope.setUserType(map.get("userType"));
            officialdocumentpushscopeMapper.insert(officialdocumentpushscope);

        }
    }


}
