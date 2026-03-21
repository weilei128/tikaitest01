package com.pcitc.legalAffairs.service.dps;

/***
 * 工作流审批过程中退回接口
 */
public interface DpsReturn<T> {

    String getCategoryCode();

    void execute(T t);

}
