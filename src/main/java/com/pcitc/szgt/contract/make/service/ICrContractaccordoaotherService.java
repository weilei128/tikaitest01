package com.pcitc.szgt.contract.make.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.make.entity.CrContractaccordoaother;
import com.pcitc.szgt.contract.make.modelEx.Accord;
import com.pcitc.szgt.contract.make.modelEx.AccordQuery;

/**
 * <p>
 * 签约依据
 * </p>
 *
 * @author ziran.zhou
 * @since 2020-02-18
 */
public interface ICrContractaccordoaotherService extends IService<CrContractaccordoaother> {
    /*
     * 签约依据保存
     * */
    boolean addAccord(Accord accord);

    /*
     * 签约依据修改
     * */
    boolean updateAccord(Accord accord);

    /*
     * 签约依据删除
     * */
    int delAccord(String Id);

    /*
     * 根据ID获取签约依据信息
     * */
    DataResult getAccordById(String Id);

    /*
     *签约依据查询
     * */
    DataResult queryAccord(String accordCode, String accordName, Integer accordSource, Integer accordType,
                           String createdBy, String userName, String orgID, Integer useCount, Integer ageNum, Integer pageSize, Integer isValid);
}
