package com.pcitc.legalAffairs.dbService.person;

import java.util.List;

import com.pcitc.legalAffairs.bo.person.PersonQulificationSummaryBo;
import com.pcitc.legalAffairs.po.person.FwPersonInfo;
import com.pcitc.szgt.legalAffairs.base.IBaseService;

public interface IPersonInfoService extends IBaseService<FwPersonInfo>  {
	public List<PersonQulificationSummaryBo> qulificationSummary();
}
