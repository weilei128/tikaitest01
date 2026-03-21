package com.pcitc.szgt.contract.sysmanager.controller;

import com.pcitc.ssc.dps.inte.workflow.PagedList;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.offeree.model.OffereeBankVo;
import com.pcitc.szgt.contract.offeree.model.OffereeLinkmanVo;
import com.pcitc.szgt.contract.offeree.service.MainDataService;
import com.pcitc.szgt.contract.offeree.service.MdmService;
import com.pcitc.szgt.contract.share.entity.SysDictionary;
import com.pcitc.szgt.contract.share.entity.SysDictionarycategory;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.model.TaskQueryModel;
import com.pcitc.szgt.contract.share.request.DictionaryRequest;
import com.pcitc.szgt.contract.share.request.DpsRequest;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import com.pcitc.szgt.excel.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    private DictionaryRequest dictionaryRequest;

    @Autowired
    private OrganizationRequest organizationRequest;

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private DpsRequest dpsRequest;

    @PostMapping(value = "ok")
    public String ok(@RequestBody Map<String, Object> bodyParam, @RequestParam String uriParam) {
        System.out.println("bodyParam: " + bodyParam);
        System.out.println("uriParam: " + uriParam);
//        return "欧尅";
        return "ok";
    }

    @PostMapping("ok2")
    public String ok2(@RequestBody List<String> params, @RequestParam String uriParam) {
        System.out.println(params);
        System.out.println(uriParam);
        return "ok2";
    }

    @GetMapping("ok3")
    public String ok3(@RequestParam String param1, @RequestParam String param2) {
        System.out.println(param1);
        System.out.println(param2);
        return "ok3";
    }

    @GetMapping("ok4")
    public String ok4() {
        return "ok4";
    }

    @PostMapping("ok5")
    public String ok5(@RequestBody String map) {
        System.out.println(map);
        return "ok5";
    }

    @PostMapping("ok6")
    public SysOrganization ok6(@RequestParam Integer orgId) {
        return organizationRequest.queryTopOrg(orgId);
    }

    @GetMapping("ok7")
    public SysUserinfo ok7() {
        return userInfoRequest.queryById(1);
    }

    @GetMapping("ok8")
    public SysDictionarycategory ok8(String id) {
        SysDictionarycategory sysDictionarycategory = dictionaryRequest.queryCategoryById(Integer.parseInt(id));
        return sysDictionarycategory;
    }

    @GetMapping("ok9")
    public List<SysDictionarycategory> ok9(String id) {
        List<SysDictionarycategory> sysDictionarycategories = dictionaryRequest.querySubCategorys(Integer.parseInt(id));
        return sysDictionarycategories;
    }

    @GetMapping("ok10")
    public List<SysDictionary> ok10(String cid) {
        List<SysDictionary> sysDictionarys = dictionaryRequest.queryDictionaryByCid(cid);
        return sysDictionarys;
    }

    @GetMapping("ok11")
    public SysDictionary ok11(String did) {
        SysDictionary sysDictionary = dictionaryRequest.queryDictionary(did);
        return sysDictionary;
    }

    @GetMapping("ok12")
    public List<SysUserinfo> ok12() {
        List<SysUserinfo> sysUserinfos = userInfoRequest.queryAll();
        return sysUserinfos;
    }

    @GetMapping("ok13")
    public List<SysUserinfo> ok13() {
        List<SysUserinfo> sysUserinfos = userInfoRequest.queryByOrgId(1);
        return sysUserinfos;
    }

    @GetMapping("ok14")
    public SysDictionarycategory ok14() {
        return dictionaryRequest.queryCategotyByCode("hetong");
    }

    @GetMapping("ok15")
    public List<SysDictionarycategory> ok15() {
        List<SysDictionarycategory> hetong = dictionaryRequest.querySubCategoryByCode("hetong");
        return hetong;
    }

    @GetMapping("ok16")
    public PagedList ok16() {
        PagedList pageddonetask = dpsRequest.pagedtodotask("1", 1, 10, null, null, null);
        return pageddonetask;
    }

    @GetMapping("ok17")
    public PagedList ok17(){
        TaskQueryModel taskQueryModel = new TaskQueryModel();
        taskQueryModel.setCategoryCode("szgt_contract_msgtest,szgt_contract_callbacktest");
        taskQueryModel.setCurrent(1);
        taskQueryModel.setSize(10);
        taskQueryModel.setQuery("ext001='contract'");
        taskQueryModel.setIncludeMsg(1);
        return dpsRequest.pagedtodotask(taskQueryModel);
    }

    @Autowired
    private MdmService mdmService;

    @Autowired
    private MainDataService mainDataService;

    @GetMapping("ok18")
    public void ok18(){
//        mdmService.tranfer("", "", 100, 1);
    }

    @GetMapping("ok19")
    public void ok19(){
        mainDataService.fetchMainData();
    }

    @GetMapping("ok20")
    public void ok20(HttpServletResponse response) {

        String sheet1 = "标题标题";

        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("银行账号", "BankAcount");
        lhm.put("银行编码", "BankCode");
        lhm.put("银行id", "BankID");
        lhm.put("银行名称", "BankName");
        lhm.put("银行UK", "BankUK");
        lhm.put("创建人", "CreatedBy");
        lhm.put("修改人", "ModifiedBy");
        lhm.put("创建时间", "CreatedDate");

        List<OffereeBankVo> list = new ArrayList<>();
        for(int i =0;i<10;i++){
            OffereeBankVo offereeBankVo = new OffereeBankVo();
            offereeBankVo.setModifiedBy("xxx" + i);
            offereeBankVo.setCreatedBy("fff" + i);
            offereeBankVo.setBankUK("bankUK" + i);
            offereeBankVo.setBankName("bankName" + i);
            offereeBankVo.setBankCode("bankCode" + i);
            offereeBankVo.setBankID("bankId" + i);
            offereeBankVo.setBankAcount("bankAccount" + i);
            offereeBankVo.setCreatedDate(new Date());
//            offereeBankVo.setExportVal("exportVal" + i);
            list.add(offereeBankVo);
        }

        byte[] export = ExcelUtil.export(sheet1, false, lhm, list);

        String filename = "文件名";
        try {
            filename = java.net.URLEncoder.encode(filename.trim(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment; filename=" + filename + ".xlsx");
        response.setHeader("content-length", export.length + "");
        try {
            response.getOutputStream().write(export);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @PostMapping("ok21")
    public void ok21(MultipartFile file) throws IOException {
        LinkedHashMap<String, String> lhm = new LinkedHashMap<>();
        lhm.put("银行账号", "bankAcount");
        lhm.put("银行编码", "bankCode");
        lhm.put("银行id", "bankID");
        lhm.put("银行名称", "bankName");
        lhm.put("银行UK", "bankUK");
        lhm.put("创建人", "createdBy");
        lhm.put("修改人", "modifiedBy");
        lhm.put("创建时间", "createdDate");
//        lhm.put("导出测试字段", "exportVal");
        List<OffereeBankVo2> maps = ExcelUtil.readExcel(file.getOriginalFilename(), lhm, OffereeBankVo2.class, file.getInputStream(), 0,0);
        System.out.println(maps);
    }


//    @GetMapping("manualMDMsyn")
//    public DataResult manualMDMsyn(){
//        mainDataService.fetchMainDataManual();
//        return DataResult.success(null);
//    }
}
