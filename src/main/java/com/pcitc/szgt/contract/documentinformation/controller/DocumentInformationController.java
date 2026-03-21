package com.pcitc.szgt.contract.documentinformation.controller;

import com.alibaba.fastjson.JSON;
import com.pcitc.szgt.contract.documentinformation.model.Officialdocumentdetailmessage;
import com.pcitc.szgt.contract.documentinformation.model.Officialdocumentmessage;
import com.pcitc.szgt.contract.documentinformation.service.IDocumentInformationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "DocumentInformationController",tags = "公文信息")
@RestController
@RequestMapping("/documentinformation")
@Slf4j
public class DocumentInformationController {

@Autowired
IDocumentInformationService IDocumentInformationService;

    /**
     * 接收OA推送过来de公文信息信息
     *
     * @param
     * @return
     */
    @PostMapping("callBackDocument")
    @ApiOperation(value = "接收OA推送过来数据")
    public String callBackDocument(@RequestBody Officialdocumentdetailmessage officialdocumentdetailmessage) {
        return JSON.toJSONString(IDocumentInformationService.callBackDocument(officialdocumentdetailmessage));
    }

    /**
     * 公文信息测试保存
     *
     * @param
     * @return
     */
    @PostMapping("callBackDocumentSave")
    public String callBackDocumentSave(@RequestBody Officialdocumentdetailmessage officialdocumentdetailmessage) {
        return JSON.toJSONString(IDocumentInformationService.callBackDocumentSave(officialdocumentdetailmessage));
    }
}
