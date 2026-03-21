package com.pcitc.szgt.contract.textmanage.controller;

import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.textmanage.entity.CrContracttextmodel;
import com.pcitc.szgt.contract.textmanage.model.*;
import com.pcitc.szgt.contract.textmanage.service.ICrContracttextmodelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("textmodel")
public class TextModelController {

    @Autowired
    ICrContracttextmodelService service;

    @PostMapping("apply")
    public DataResult textApply(@RequestBody TextApplyVo textApplyVo){
        return service.textApply(textApplyVo);
    }

    @PostMapping("edit")
    public DataResult textEdit(@RequestBody TextEditVo textEditVo){
        return service.textEdit(textEditVo);
    }

    @PostMapping("discard")
    public DataResult textDiscard(@RequestBody TextDiscardVo textDiscardVo){
        return service.textDiscard(textDiscardVo);
    }

    @PostMapping("del")
    public DataResult textDraftDel(@RequestBody TextDraftDelVo textDraftDelVo){
        return service.textDraftDel(textDraftDelVo);
    }

    @GetMapping("query")
    public DataResult<PageData<TextDetailResultVo>> textQuery(TextQueryVo textQueryVo){
        return service.textQuery(textQueryVo);
    }

    @GetMapping("detail")
    public DataResult<TextDetailResultVo> textDetail(@RequestParam String textId){
        return service.textDetail(textId);
    }

    @GetMapping("history")
    public DataResult<List<TextDetailResultVo>> historyList(@RequestParam String textId){
        return service.historyList(textId);
    }
}
