package com.oo.reportforms.domain.vo;

import lombok.Data;

@Data
public class DcReportDesionCensorScreenExamination {
    public String censorName;//专家名称
    public String deepWater;//深水
    public String UnderDeepWaterRocks;//深水岩下
    public String LandSaltGypsumLayer;//陆地盐膏层
    public String oilSand;//油砂
    public String largeDisplacement;//大位移
    public String HighTemperatureHighPressure;//高温高压
    public String other;//其它
    public String amountTo;//合计
}
