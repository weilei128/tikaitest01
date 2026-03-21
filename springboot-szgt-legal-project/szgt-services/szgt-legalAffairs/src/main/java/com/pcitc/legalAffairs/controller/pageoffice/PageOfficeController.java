package com.pcitc.legalAffairs.controller.pageoffice;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.legalAffairs.service.pageoffice.PageOfficeService;
import com.pctic.common.utils.UserUtils;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("pageoffice")
public class PageOfficeController {

	@Autowired
	private PageOfficeService pageOfficeService;
	
	@GetMapping("/word")
	public String showWord(HttpServletRequest req, Map<String, String> map) {
		String fileUrl = map.get("file");
		String token = map.get("access_token");
		System.out.println("url: " + fileUrl);
		System.out.println("token: " + token);
		PageOfficeCtrl ctrl = new PageOfficeCtrl(req);
		ctrl.setServerPage("/poserver.zz");
		ctrl.webOpen("D:\\1_蓝图设计补充说明0319.docx", OpenModeType.docReadOnly, "张三");
		map.put("pageoffice", ctrl.getHtmlCode("1"));
		return "pageoffice/wordFile";
	}
	
	@RequestMapping("newWord")
	public String toNewWord(@RequestParam Map<String, Object> map, Model model) {
		model.addAllAttributes(map);
		return "pageoffice/jump";
	}
	
	/**
	 * (前端用)
	 */
	@ApiOperation("显示PageOffice控件")
	@GetMapping("view")
	public String view(@RequestParam Map<String, String> map, HttpServletRequest request, Model model) throws IOException {
		SysUserInfo userinfo = UserUtils.getUserInfo();
		return pageOfficeService.openFile(map, userinfo, request, model);
	}
}
