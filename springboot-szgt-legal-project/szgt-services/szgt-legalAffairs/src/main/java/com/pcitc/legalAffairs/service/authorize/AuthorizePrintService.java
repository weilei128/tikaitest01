package com.pcitc.legalAffairs.service.authorize;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.BeanUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pcitc.common.entity.Result;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.bo.authorize.DpsLogBo;
import com.pcitc.legalAffairs.dbService.authorize.IAuthorizeInfoService;
import com.pcitc.legalAffairs.po.authorize.FwAuthorizeInfo;
import com.pcitc.legalAffairs.service.dps.http.DpsHttpService;
import com.pctic.common.utils.DateUtils;
import com.zhuozhengsoft.pageoffice.DocumentVersion;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;
import com.zhuozhengsoft.pageoffice.wordwriter.Cell;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegion;
import com.zhuozhengsoft.pageoffice.wordwriter.DataRegionInsertType;
import com.zhuozhengsoft.pageoffice.wordwriter.Table;
import com.zhuozhengsoft.pageoffice.wordwriter.WdAutoFitBehavior;
import com.zhuozhengsoft.pageoffice.wordwriter.WdParagraphAlignment;
import com.zhuozhengsoft.pageoffice.wordwriter.WordDocument;

/**
 * 授权打印Service
 * 
 * @author meihongli
 *
 */
@Service
public class AuthorizePrintService {

	@Autowired
	private IAuthorizeInfoService iinfoService;
	@Autowired
	private DpsHttpService dpsHttpService;
	private static final DateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/**
	 * 工作流服务的地址
	 */
	@Value("${dps.url}")
	private String server;
	/**
	 * 工作流中appid的前缀
	 */
	private String authorizeDpsPrefix = "szgt_fawu_authorization_app_";

	/**
	 * 打印授权书
	 * 
	 * @param id
	 * @return
	 */
	public PageOfficeCtrl printAuthorization(HttpServletRequest req, String id) {
		FwAuthorizeInfo entity = iinfoService.getById(id);
		if (entity == null) {
			throw new BaseException("找不到授权", 400);
		}
		String template = entity.getfTemplatePath();
		if (template == null || template.length() == 0) {
			throw new BaseException("没有有效的模板", 400);
		}
		WordDocument writer = new WordDocument();
		DataRegion authorizer = writer.openDataRegion("授权人");
		authorizer.setValue(entity.getFkAuthorizerName());
		DataRegion licensee = writer.openDataRegion("被授权人");
		licensee.setValue(entity.getfLicenseeName());
		DataRegion matter = writer.openDataRegion("授权事项");
		matter.setValue(entity.getfAuthorizeMatters());
		DataRegion limit = writer.openDataRegion("授权期限");
		StringBuilder limitString = new StringBuilder("");
		if (entity.getfAuthorizeLimit() != null && entity.getfAuthorizeLimit().length() > 0) {
			String[] split = entity.getfAuthorizeLimit().split("|");
			if ("无固定期限".equals(split[0])) {
				if (split.length >= 2) {
					limitString.append(split[1]);
				} else {
					limitString.append("---");
				}
			} else {
				if (split.length >= 2) {
					limitString.append(split[1].equals("ddd")? "---": split[1]);
					limitString.append(" ~ ");
				}
				if (split.length < 3 && !split[2].equals("ddd")) {
					limitString.append("---");
				} else {
					limitString.append(split[2]);
				}
			}
		}
		limit.setValue(limitString.toString());
		LocalDate date = LocalDate.now();
		DataRegion yr = writer.openDataRegion("年");
		yr.setValue(String.valueOf(date.getYear()));
		DataRegion mo = writer.openDataRegion("月");
		mo.setValue(String.valueOf(date.getMonthValue()));
		DataRegion dd = writer.openDataRegion("日");
		dd.setValue(String.valueOf(date.getDayOfMonth()));

		PageOfficeCtrl ctrl = new PageOfficeCtrl(req);
		ctrl.setServerPage("/poserver.zz");
		ctrl.setWriter(writer);
		// 测试用
//		ctrl.webOpen("D:\\授权委托书模板（组织）.docx", OpenModeType.docReadOnly, "张三");
		ctrl.webOpen(template, OpenModeType.docReadOnly, "张三");
		return ctrl;
	}

	/**
	 * 获取审批信息
	 * 
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Result getApproveLog(String id) {
		RestTemplate restTemplate = new RestTemplate();
		String url = server + "/dps/opinion?businessId={businessId}";
		Map<String, Object> params = new HashMap<>();
		params.put("businessId", authorizeDpsPrefix + id);
		// params.put("businessId", id);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		// 调试用
		// headers.set("Authorization", "bearer a61700cb-704b-4c5d-a518-c3640feb1621");

		if (!StringUtils.isEmpty(dpsHttpService.getAuthorization())) {
			headers.set("Authorization", dpsHttpService.getAuthorization());
		}
		HttpEntity<JSONObject> entity = new HttpEntity<>(new JSONObject(), headers);
		HttpEntity<Result> response = null;
		response = restTemplate.exchange(url, HttpMethod.GET, entity, Result.class, params);
		Result result = response.getBody();
		// Result result = new Result();
		if (!result.isSuccess()) {
			throw new BaseException(result.getMsg(), result.getCode());
			// result = new Result();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DpsLogBo> resultToBo(Result result) {
		Object data = result.getData();
		if (data == null) {
			return new ArrayList<>();
		}
		List<DpsLogBo> list = BeanUtils.mapsToBeans((List<Map<String, Object>>) data, DpsLogBo.class);
		return list;
	}

	/**
	 * 打印会签单
	 * 
	 * @return
	 */
	public PageOfficeCtrl printApproveChart(HttpServletRequest req, String id) {
		FwAuthorizeInfo entity = iinfoService.getById(id);
		if (entity == null) {
			throw new BaseException("找不到授权", 400);
		}
		List<DpsLogBo> dps = resultToBo(getApproveLog(id));
		WordDocument writer = new WordDocument();
		// 标题
		DataRegion title = writer.createDataRegion("title", DataRegionInsertType.After, "[HOME]");
		// 居中
		title.getParagraphFormat().setAlignment(WdParagraphAlignment.wdAlignParagraphCenter);
		// 粗体
		title.getFont().setBold(true);
		title.setValue("审查审批表");
		// 表格
		DataRegion chartRegion = writer.createDataRegion("chart", DataRegionInsertType.After, "title");
		chartRegion.getFont().setBold(false);
		chartRegion.getParagraphFormat().setAlignment(WdParagraphAlignment.wdAlignParagraphCenter);
		Table table = chartRegion.createTable(2, 4, WdAutoFitBehavior.wdAutoFitWindow);
		Cell cell = table.openCellRC(1, 1);
		cell.getParagraphFormat().setAlignment(WdParagraphAlignment.wdAlignParagraphRight);
		cell.setValue("申请时间");
		cell = table.openCellRC(1, 2);
		cell.setValue(DateUtils.dateToString(entity.getfApplyTime(), FORMAT));
		cell.getParagraphFormat().setAlignment(WdParagraphAlignment.wdAlignParagraphLeft);

		cell = table.openCellRC(1, 3);
		cell.setValue("流水序号");
		cell.getParagraphFormat().setAlignment(WdParagraphAlignment.wdAlignParagraphLeft);
		cell = table.openCellRC(1, 4);
		cell.setValue(entity.getfSerialNo());
		cell.getParagraphFormat().setAlignment(WdParagraphAlignment.wdAlignParagraphRight);

		cell = table.openCellRC(2, 1);
		cell.setValue("经办人");
		cell = table.openCellRC(2, 2);
		cell.setValue(entity.getfManagerName());

		cell = table.openCellRC(2, 3);
		cell.setValue("经办单位/部门");
		cell = table.openCellRC(2, 4);
		cell.setValue(entity.getFkAuthorizerOrgName());

		DataRegion chartRegion2 = writer.createDataRegion("chart2", DataRegionInsertType.After, "chart");
		Table table2 = chartRegion2.createTable(9, 2, WdAutoFitBehavior.wdAutoFitWindow);

		cell = table2.openCellRC(1, 1);
		cell.setValue("授权书编号");
		cell = table2.openCellRC(1, 2);
		cell.setValue(entity.getfAuthorizationNo() == null ? "" : entity.getfAuthorizationNo());

		cell = table2.openCellRC(2, 1);
		cell.setValue("授权人属性");
		cell = table2.openCellRC(2, 2);
		cell.setValue(entity.getFkAuthorizerOrgType() == null ? "" : entity.getFkAuthorizerOrgType());

		cell = table2.openCellRC(3, 1);
		cell.setValue("授权人");
		cell = table2.openCellRC(3, 2);
		cell.setValue(entity.getFkAuthorizerName() == null ? "" : entity.getFkAuthorizerName());

		cell = table2.openCellRC(4, 1);
		cell.setValue("法定代表/负责人");
		cell = table2.openCellRC(4, 2);
		cell.setValue(entity.getFkLegalRepresentative() == null ? "" : entity.getFkLegalRepresentative());

		cell = table2.openCellRC(5, 1);
		cell.setValue("授权类型");
		cell = table2.openCellRC(5, 2);
		cell.setValue(entity.getfType() == null ? "" : entity.getfType());

		cell = table2.openCellRC(6, 1);
		cell.setValue("授权期限");
		cell = table2.openCellRC(6, 2);
		cell.setValue(entity.getfAuthorizeLimit() == null ? "" : entity.getfAuthorizeLimit());

		cell = table2.openCellRC(7, 1);
		cell.setValue("授权事项");
		cell = table2.openCellRC(7, 2);
		cell.setValue(entity.getfAuthorizeMatters() == null ? "" : entity.getfAuthorizeMatters());

		cell = table2.openCellRC(8, 1);
		cell.setValue("注意事项");
		cell = table2.openCellRC(8, 2);
		cell.setValue(entity.getfCaution() == null ? "" : entity.getfCaution());

		cell = table2.openCellRC(9, 1);
		cell.setValue("备注");
		cell = table2.openCellRC(9, 2);
		cell.setValue(entity.getfRemark() == null ? "" : entity.getfRemark());

		DataRegion chartRegion3 = writer.createDataRegion("chart3", DataRegionInsertType.After, "chart2");
		Table table3 = chartRegion3.createTable(2, 3, WdAutoFitBehavior.wdAutoFitWindow);

		cell = table3.openCellRC(1, 1);
		cell.setValue("被授权人");
		cell = table3.openCellRC(1, 2);
		cell.setValue("法定代表人/负责人");
		cell = table3.openCellRC(1, 3);
		cell.setValue("统一社会信用代码");

		cell = table3.openCellRC(2, 1);
		cell.setValue(entity.getfLicenseeName() == null ? "" : entity.getfLicenseeName());
		cell = table3.openCellRC(2, 2);
		cell.setValue(entity.getfLicenseeLegalRepresentative() == null ? "" : entity.getfLicenseeLegalRepresentative());
		cell = table3.openCellRC(2, 3);
		cell.setValue(entity.getfUscCode() == null ? "" : entity.getfUscCode());
		
		// 审批记录
		if (dps != null && dps.size() > 0) {
			DataRegion dpsAreaRegion = writer.createDataRegion("chart_dps", DataRegionInsertType.After, "chart3");
			Table dpsTable = dpsAreaRegion.createTable(1 + dps.size(), 3, WdAutoFitBehavior.wdAutoFitWindow);
			int rowNo = 1;
			cell = dpsTable.openCellRC(rowNo, 1);
			cell.setValue("审批人");
			cell = dpsTable.openCellRC(rowNo, 2);
			cell.mergeTo(rowNo, 3);
			cell.setValue("审批意见");
			for (DpsLogBo each: dps) {
				rowNo++;
				cell = dpsTable.openCellRC(rowNo, 1);
				cell.setValue(each.getExecutorName());
				cell = dpsTable.openCellRC(rowNo, 2);
				cell.mergeTo(rowNo, 3);
				StringBuilder builder = new StringBuilder();
				builder.append(each.getDataState() == 0? "拒绝": "通过");
				builder.append("\r\n");
				builder.append(each.getOpinion());
				builder.append("\r\n");
				builder.append(DateUtils.dateToString(each.getExecuteDate(), new SimpleDateFormat("yyyy-MM-dd")));
			}
		}
		
		writer.setEnableAllDataRegionsEditing(false);
		

		PageOfficeCtrl ctrl = new PageOfficeCtrl(req);
		ctrl.setServerPage("/poserver.zz");
		ctrl.setWriter(writer);
		ctrl.webCreateNew("审查审批表", DocumentVersion.Word2007);
		return ctrl;
	}
}
