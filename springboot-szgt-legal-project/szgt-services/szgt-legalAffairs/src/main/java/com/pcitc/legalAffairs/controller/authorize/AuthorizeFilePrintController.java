package com.pcitc.legalAffairs.controller.authorize;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pcitc.legalAffairs.service.authorize.AuthorizePrintService;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

/**
 * 授权文件打印Controller
 * @author meihongli
 *
 */
@Controller
@RequestMapping("authorize/print")
public class AuthorizeFilePrintController {

	@Autowired
	private AuthorizePrintService printService;
	
	/**
	 * 跳转至打印授权书
	 * @param map
	 * @param model
	 * @return
	 */
	@GetMapping("authorization")
	public String toAuthorization(@RequestParam Map<String, Object> map, Model model) {
		model.addAllAttributes(map);
		return "authorization/jumpToAuthorization";
	}

	/**
	 * 打印授权书
	 * @param req
	 * @return
	 */
	@GetMapping("printAuthorization")
	public String printAuthorization(HttpServletRequest req, Map<String, Object> map) {
		String id = req.getParameter("id");
		PageOfficeCtrl ctrl = printService.printAuthorization(req, id);
		ctrl.setAllowCopy(false);
		ctrl.setMenubar(false);
		ctrl.setOfficeToolbars(false);
		ctrl.setTitlebar(false);
		ctrl.addCustomToolButton("保存", "Save", 1);
		ctrl.addCustomToolButton("打印", "PrintOut", 6);
		map.put("pageoffice", ctrl.getHtmlCode("pageoffice1"));
		return "authorization/authorization";
	}
	
	/**
	 * 跳转至打印会签单
	 * @param map
	 * @param model
	 * @return
	 */
	@GetMapping("approvement")
	public String toApprovement(@RequestParam Map<String, Object> map, Model model) {
		model.addAllAttributes(map);
		return "authorization/jumpToApprovement";
	}
	
	/**
	 * 打印会签单
	 * @param req
	 * @return
	 */
	@GetMapping("printApprovement")
	public String printApprovement(HttpServletRequest req, Map<String, Object> map) {
		String id = req.getParameter("id");
		PageOfficeCtrl ctrl = printService.printApproveChart(req, id);
		ctrl.setAllowCopy(false);
		ctrl.setMenubar(false);
		ctrl.setOfficeToolbars(false);
		ctrl.setTitlebar(false);
		ctrl.addCustomToolButton("保存", "Save", 1);
		ctrl.addCustomToolButton("打印", "PrintOut", 6);
		map.put("pageoffice", ctrl.getHtmlCode("pageoffice1"));
		ctrl.setTagId("1");
		return "authorization/approvement";
	}
	
//	@GetMapping("demo")
//	@ResponseBody
//	public Result demo(@RequestParam String id) {
//		Result log = printService.getApproveLog(id);
//		return log;
//	}
}
