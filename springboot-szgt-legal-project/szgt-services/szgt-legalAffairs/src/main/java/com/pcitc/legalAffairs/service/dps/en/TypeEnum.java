package com.pcitc.legalAffairs.service.dps.en;

/***
 * @description 2020年3月18日 14:47:23
 * @author leigang
 * @date 2020年3月18日 14:43:19
 *
 */
public enum TypeEnum {

    ExecuteFinish(1, "审批完成"),
    ExecuteRevert(2, "审批退回"),
    ExecuteRevertPoint(3, "选择退回"),
    Execute(4, "活动/参与者完成"),
    ExecuteWithdraw(5, "撤销"),
    start(6, "流程发起"),
    forward(7, "处理待办结果"),
    beforeActivate(8, "活动激活前"),
    afterComplete(9, "活动完成后"),
    sendTask(10, "发送待办"),
    ExecuteBackRevert(11, "退回返回"),
    Finish(1, "审批完成"),
    Revert(2, "审批退回"),
    ApprovalIng(0, "审批中");

    private int type;

    private String des;

    TypeEnum(int type, String des) {
        this.type = type;
        this.des = des;
    }

    public int getType() {
        return type;
    }

    public String getDes() {
        return des;
    }
}
