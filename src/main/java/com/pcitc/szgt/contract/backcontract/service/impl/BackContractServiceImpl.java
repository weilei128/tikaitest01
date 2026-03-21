package com.pcitc.szgt.contract.backcontract.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pcitc.szgt.contract.backcontract.entity.ContractQuery;
import com.pcitc.szgt.contract.backcontract.service.BackContractService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.common.PageData;
import com.pcitc.szgt.contract.config.CmisDefaultConfig;
import com.pcitc.szgt.contract.make.entity.CrContractbasic;
import com.pcitc.szgt.contract.make.entity.CrContractofferee;
import com.pcitc.szgt.contract.make.mapper.CrContractbasicMapper;
import com.pcitc.szgt.contract.make.mapper.CrContractoffereeMapper;
import com.pcitc.szgt.contract.offeree.entity.FfOffereeinfo;
import com.pcitc.szgt.contract.offeree.mapper.FfOffereeinfoMapper;
import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;
import com.pcitc.szgt.contract.share.request.OrganizationRequest;
import com.pcitc.szgt.contract.share.request.UserInfoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 臧传军
 * @date 2021-01-20 17:18:29
 **/
@Service
public class BackContractServiceImpl implements BackContractService {
    @Autowired
    private CrContractbasicMapper crContractbasicMapper;

    @Autowired
    private CmisDefaultConfig cmisDefaultConfig;

    @Autowired
    private CrContractoffereeMapper crContractoffereeMapper;

    @Autowired
    private FfOffereeinfoMapper ffOffereeinfoMapper;

    @Autowired
    private UserInfoRequest userInfoRequest;

    @Autowired
    private OrganizationRequest organizationRequest;



    @Override
    public DataResult<?> list(ContractQuery contractQuery){
        if (contractQuery.getPageNum() <= 0) {
            contractQuery.setPageNum(cmisDefaultConfig.getPageNum());
        }
        if (contractQuery.getPageSize() <= 0) {
            contractQuery.setPageSize(cmisDefaultConfig.getPageSize());
        }

        List<Object> objectList = new ArrayList<>();
        IPage<HashMap> page = new Page<>(contractQuery.getPageNum(), contractQuery.getPageSize());
        QueryWrapper<CrContractbasic> crContractbasicQueryWrapper = new QueryWrapper<>();

        //判断是否为审批完成
        crContractbasicQueryWrapper.lambda().isNotNull(CrContractbasic::getCheckDate);
        if (contractQuery.getContractName() != null && !StringUtils.isEmpty(contractQuery.getContractName())) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractName, contractQuery.getContractName().trim());
        }
        if (contractQuery.getContractNum() != null && !StringUtils.isEmpty(contractQuery.getContractNum())) {
            crContractbasicQueryWrapper.lambda().like(CrContractbasic::getContractNum, contractQuery.getContractNum().trim());
        }
        if(contractQuery.getRuleSerialNum()!=null && !StringUtils.isEmpty(contractQuery.getRuleSerialNum())){
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getRuleSerialNum,contractQuery.getRuleSerialNum());
        }
        if(contractQuery.getMainDeptID()!=null && !StringUtils.isEmpty(contractQuery.getMainDeptID())){
            crContractbasicQueryWrapper.lambda().eq(CrContractbasic::getMainDeptID,contractQuery.getMainDeptID());
        }
        List<HashMap> list = crContractbasicMapper.queryContractPayment(page, crContractbasicQueryWrapper);

        Integer[] mainDeptIDS = list.stream().map(i -> (Integer) i.get("MainDeptID")).toArray(Integer[]::new);
        List<SysOrganization> sysOrganizations = organizationRequest.queryOrgByIdBatch(mainDeptIDS);
        Map<Integer, SysOrganization> mainDeptMap = sysOrganizations.stream().
                collect(Collectors.toMap(SysOrganization::getfId, Function.identity(), (k1, k2) -> k1));

        if (list != null && list.size() > 0) {
            for (HashMap map : list) {
                JSONObject obj = new JSONObject(true);
                obj.put("contractID", map.get("ContractID"));//合同id
                obj.put("ruleserialNum", map.get("RuleSerialNum"));//合同序号
                obj.put("contractName", map.get("ContractName"));//合同名称
                obj.put("contractNum", map.get("ContractNum"));//合同编码
                //合同相对人信息
                String offereeId = "";
                String offereeName = "";
                QueryWrapper<CrContractofferee> contractoffereeQueryWrapper = new QueryWrapper<>();
                contractoffereeQueryWrapper.lambda().eq(CrContractofferee::getContractID, map.get("ContractID"));
                List<CrContractofferee> crContractoffereeList = crContractoffereeMapper.selectList(contractoffereeQueryWrapper);
                if (crContractoffereeList != null && crContractoffereeList.size() > 0) {
                    for (CrContractofferee crContractofferee : crContractoffereeList) {
                        FfOffereeinfo ffOffereeinfo = ffOffereeinfoMapper.selectById(crContractofferee.getOffereeID());
                        if (ffOffereeinfo != null) {
                            offereeId += ffOffereeinfo.getOffereeId() + ";";
                            offereeName += ffOffereeinfo.getOffereeName() + ";";
                        }
                    }
                }
                if (!StringUtils.isEmpty(offereeId)) {
                    offereeId = offereeId.substring(0, offereeId.length() - 1);
                }
                if (!StringUtils.isEmpty(offereeName)) {
                    offereeName = offereeName.substring(0, offereeName.length() - 1);
                }
                obj.put("offereeId", offereeId);//相对人id
                obj.put("offereeName", offereeName);//相对人名称


                obj.put("createdBy", map.get("MainOrgUserID"));//经办人id
                String createdByName = "";
                if (map.get("MainOrgUserID") != null && !StringUtils.isEmpty(map.get("MainOrgUserID"))) {
                    SysUserinfo sysUserinfo = userInfoRequest.queryById(Integer.parseInt(map.get("MainOrgUserID").toString()));
                    if (sysUserinfo != null) {
                        createdByName = sysUserinfo.getfCname();
                    }
                }
                obj.put("createdByName", createdByName);//经办人名称
                obj.put("createdDate", map.get("CreatedDate"));//创建时间
                obj.put("CheckDate", map.get("CheckDate"));//合同审查审批完成时间
                obj.put("BUDAT", map.get("BUDAT"));//凭证过帐日期(最早收/付款日期)
                obj.put("DMBTR", map.get("DMBTR"));//凭证过账金额(已收/付款金额)
                obj.put("ContractObjectMoney", map.get("ContractObjectMoney"));  //合同金额(标的金额)
                obj.put("mainDeptId", map.get("MainDeptID"));
                Integer mainDeptID = (Integer) map.get("MainDeptID");
                obj.put("mainDeptName", mainDeptMap.get(mainDeptID).getfName());
                objectList.add(obj);
            }
        }

        PageData<Object> pageData = new PageData<>();
        pageData.setCurrentPage(page.getCurrent());
        pageData.setPageSize(page.getSize());
        pageData.setTotalCount(page.getTotal());
        pageData.setTotalPage(page.getPages());
        pageData.setData(objectList);
        return DataResult.success(pageData);
    }
}
