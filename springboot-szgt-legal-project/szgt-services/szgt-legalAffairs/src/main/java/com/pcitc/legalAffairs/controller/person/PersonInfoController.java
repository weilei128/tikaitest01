package com.pcitc.legalAffairs.controller.person;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pcitc.common.entity.Result;
import com.pcitc.legalAffairs.bo.person.PersonInfoBo;
import com.pcitc.legalAffairs.bo.person.PersonQueryBo;
import com.pcitc.legalAffairs.bo.person.PersonQulificationSummaryBo;
import com.pcitc.legalAffairs.easypoi.utils.OfficeExportUtil;
import com.pcitc.legalAffairs.po.person.FwPersonInfo;
import com.pcitc.legalAffairs.service.person.PersonInfoService;
import com.pcitc.legalAffairs.vo.person.FwExcelPersonVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.List;

/**
 * 法律人员管理Controller
 * @author meihongli
 *
 */
@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("person")
public class PersonInfoController {

	@Autowired
	private PersonInfoService personInfoService;

	@PostMapping("addPerson")
	public Result addPersonInfo(@RequestBody PersonInfoBo personInfo) throws ParseException {
		Long id = personInfoService.savePersonInfo(personInfo);
		Result result = Result.status(true);
		Result.data(id);
		return result;
	}
	
	/**
	 * 删除单个法律人员
	 * @param personId
	 * @return
	 */
	@PostMapping("removePerson")
	public Result removePersonInfo(@RequestParam String personId) {
		boolean success = personInfoService.removePersonInfoById(personId);
		Result result = Result.status(success);
		return result;
	}
	
	/**
	 * 批量删除法律人员
	 * @param personId
	 * @return
	 */
	@PostMapping("removePersonBatch")
	public Result removePersonInfoBatch(@RequestBody List<String> personId) {
		personInfoService.removeBatch(personId);
		Result result = Result.status(true);
		return result;
	}
	
	@PostMapping("updatePerson")
	public Result updatePersonInfo(@RequestBody PersonInfoBo personInfo) throws ParseException {
		Long id = personInfoService.updatePersonInfo(personInfo);
		Result result = Result.status(true);
		Result.data(id);
		return result;
	}

	@PostMapping("getPersonById")
	public Result getById(@RequestParam String personId) {
		PersonInfoBo personInfo = personInfoService.getById(personId);
		Result<PersonInfoBo> result = Result.data(personInfo);
		return result;
	}
	
	@PostMapping("queryPersonList")
	public Result queryPersonList(@RequestBody PersonQueryBo query) {
		IPage<FwPersonInfo> list = personInfoService.queryPersonList(query);
		return Result.data(list);
	}
	
	@PostMapping("queryPersonListBo")
	public Result queryPersonListBo(@RequestBody PersonQueryBo query) {
		IPage<PersonInfoBo> list = personInfoService.queryPersonListBo(query);
		return Result.data(list);
	}
	
	/**
	 * 设定法律从业人员
	 * @param personId
	 * @return
	 */
	@PostMapping("setPersonLegalPractitioner")
	public Result setLegalPractitionerBatch(@RequestBody List<Long> personId) {
		personInfoService.setLegalPractitioner(personId, (byte) 1);
		return Result.status(true);
	}
	
	/**
	 * 取消法律从业人员
	 * @param personId
	 * @return
	 */
	@PostMapping("setPersonNotLegalPractitioner")
	public Result setNotLegalPractitionerBatch(@RequestBody List<Long> personId) {
		personInfoService.setLegalPractitioner(personId, (byte) 0);
		return Result.status(true);
	}
	
	
	/**
	 * 法律从业人员持证统计
	 * @return
	 */
	@PostMapping("qulificationSummary")
	public Result qulificationSummary() {
		List<PersonQulificationSummaryBo> summary = personInfoService.qulificationSummary();
		return Result.data(summary);
	}

	/**
	 * 法律从业人员持证统计
	 * @return
	 */
	@GetMapping("exportExcel")
	public void exportExcel(HttpServletResponse response) {

		try{
			List<FwExcelPersonVo> voList = personInfoService.exportExcel();
			String fileName = "法律人员导出";
			Workbook workbook = OfficeExportUtil.getWorkbook("法律人员导出", "Sheet 1", FwExcelPersonVo.class, voList);
			OfficeExportUtil.exportExcel(workbook, "法律人员导出", response);
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
