package com.pcitc.szgt.contract.finality.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ContractFromLegal {

    private String disputeNo ;//纠纷编号

    private String handlingResults ;//办理结果

    private LocalDateTime closingTime ;//结案时间

    private String contractNum ;//对应合同编号


}
