package com.pcitc.legalAffairs.service.dps;

import com.pcitc.legalAffairs.service.dps.entity.StartVo;

/***
 * 工作流审批完成回调接口
 */
public interface DpsComplete<T> {

    String getCategoryCode();

    void execute(T t);

    /**
     * type = 0 验证数据是否存在
     * = 1 流程启动完成入库
     *
     * @param type
     * @param startVo
     */
    void start(int type, StartVo startVo);


}
