package com.pcitc.szgt.contract.offeree.service;

import com.pcitc.szgt.contract.offeree.model.InMainDataRespModel;
import com.pcitc.szgt.contract.offeree.model.MainDataRespModel;

import java.util.Set;

public interface MdmService {

    public Set<MainDataRespModel> fetchAllData(String strtime, String endtime, Integer pageSize, Integer pageNo);

    public void tranfer(Set<MainDataRespModel> dataSet);

    public Set<InMainDataRespModel> fetchAllInData(String strtime, String endtime, Integer pageSize, Integer pageNo);

    public void transferIn(Set<InMainDataRespModel> dataSet);

}
