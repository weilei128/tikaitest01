package com.pcitc.szgt.contract.workflow.entityExt;

/*
 * 分发参与者
 * */
public class Participant {
    public String category;//分类
    public String orderNo;//排序
    public String participantCode;//待办列表获取
    public String participantId;//待办列表获取
    public String participantType;//参与者类型 0 User 用户1 Organise 机构 2 Role 角色3 Position 岗位
    public String participantValue;//参与者ID
    public String executeType;//顺序、同时、选举 0 Sequence 顺序 1Paraller 并行  2 Vote 选举
    public String degree;//待办列表获取

}
