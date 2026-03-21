package com.pcitc.szgt.contract.make.modelEx;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/*
 * 合同准备
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrepareContract {
    public String taskId;//待办任务id
    public String contractId;//合同ID
    public String contractName;//合同名称
    public String contractNum;//合同编码
    public Integer mainDept;//合同主办单位
    public String mainDeptName;//合同主办名称
    public Integer moneyFlow;//资金流向
    public Integer moneySource;//资金来源一级
    public Integer moneySource2;//资金来源二级
    public Integer selectWay1;//选商方式一级
    public Integer selectWay2;//选商方式二级
    public Integer isGuarantee;//是否有质保金
    public List<String> accordingIds;//签约依据
    public BigDecimal isImprest;//是否有付款
    public Integer type1;//合同类型1
    public Integer type2;//合同类型2
    public Integer type3;//合同类型3
    public Integer type4;//合同类型4
    public Integer isFrameContract;//是否框架合同
    public List<String> executeOrgUserIDs;//收付款执行人
    public List<Integer> executeOrgIDs;//下发单位
    public Integer isFrameChilrenContract;//是否框架下合同
    public Integer InnerContract;//是否内部合同
    public String frameContractId;//父框架合同ID
    public BigDecimal planMoney;//计划金额
    public Integer planMoneyCurrency;//计划金额币种
    public String projectId;//所属项目
    public Integer isMakeSure;//标的金额是否确定
    public BigDecimal contractObjectMoney;//标的金额
    public Integer currentcy;//标的金额币种
    public BigDecimal contractObjectRate;//汇率
    public BigDecimal contractobjectamount;//合同金额(含税金额)
    public BigDecimal contractTaxAmount;//税额
    public BigDecimal contractNoTaxAmount;//不含税金额
    public Integer mySignBodyId;//我方签约主体
    public String mySignBodyName;//我方签约主体名称
    public String mySignPersonId;//我方签约人ID
    public String mySignPersonName;//我方签约人名称
    public String mySignPersonPhone;//我方签约人联系电话
    public String mySignPersonCard;//我方签约人身份证
    public String mySignPersonUnit;//我方签约人所在单位
    public String mySignPersonDept;//我方签约人所在部门
    public String mySignPersonPostion;//我方签约人职务
    public Integer needPrintCount;//合同打印份数
    public Integer perFormIsConfirm;//履行期限是否确认
    public LocalDateTime perFormStartDate;//履行期限开始时间
    public LocalDateTime perFormEndDate;//履行期限结束时间
    public Integer issueSolveMode;//合同纠纷解决方式
    public List<String> offereeIds;//相对人
    public List<String> materialIds;//标的明细
    public String sourceContractNum;//对方合同编号
    public String remark;//备注
    public Integer isSlaveContract; //是否从合同
    public String masterContractID; //主合同ID
    public Integer isOnlineMaster;  //是否线上合同
    public Integer reflag ;  //列表中增加一个标识,10代表是合同补录的列表信息
}
