package com.pcitc.legalAffairs.service.dps.en;

import org.springframework.util.StringUtils;

/***
 * @description 分类模板编码
 * @author leigang
 * @date 2020年3月27日 09:58:27
 *
 */
public enum DpsCategoryEnum {

    DpsCategory_Cdn("szgt_fawu_cdn", "诉求争议录入"),
    DpsCategory_Report("szgt_fawu_cdn_Report", "纠纷填报"),
    DpsCategory_Dispute("szgt_fawu_cdn_Dispute", "纠纷填报"),
    DpsCategory_Settle("szgt_fawu_cdn_Settle", "纠纷处理"),
    DpsCategory_Iaa("szgt_fawu_inter_access", "中介机构准入"),
    DpsCategory_Iae("szgt_fawu_employment", "中介机构聘用"),
    DpsCategory_Map("szgt_fawu_authorization_app", "事项授权申请"),
    DpsCategory_Punish("szgt_fawu_punish", "处罚信息填报"),
	DpsCategory_Punish_Settle("szgt_fawu_punish_settle", "处罚处理完毕");

    DpsCategoryEnum(String categoryCode, String des) {
        this.categoryCode = categoryCode;
        this.des = des;
    }

    private String categoryCode;

    private String des;

    public String getCategoryCode() {
        return categoryCode;
    }

    public String getDes() {
        return des;
    }

    public static DpsCategoryEnum getCategoryEnum(String categoryCode) {
        if (StringUtils.isEmpty(categoryCode)) {
            return null;
        }
        DpsCategoryEnum[] values = DpsCategoryEnum.values();
        for (int i = 0; i < values.length; i++) {
            DpsCategoryEnum categoryEnum = values[i];
            String enumCategoryCode = categoryEnum.getCategoryCode();
            if (categoryCode.equals(enumCategoryCode)) {
                return categoryEnum;
            }
        }
        return null;
    }
}
