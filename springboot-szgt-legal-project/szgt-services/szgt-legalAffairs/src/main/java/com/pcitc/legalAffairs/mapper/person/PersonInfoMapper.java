package com.pcitc.legalAffairs.mapper.person;

import java.util.List;

import com.pcitc.legalAffairs.bo.person.PersonQulificationSummaryBo;
import com.pcitc.legalAffairs.po.person.FwPersonInfo;
import com.pcitc.szgt.legalAffairs.base.IBaseMapper;

public interface PersonInfoMapper extends IBaseMapper<FwPersonInfo> {

	public List<PersonQulificationSummaryBo> qulificationSummary();
}
