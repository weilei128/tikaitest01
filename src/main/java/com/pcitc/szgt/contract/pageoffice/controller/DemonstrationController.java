package com.pcitc.szgt.contract.pageoffice.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.attachment.model.vo.AttachmentResultVo;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DemonstrationController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplateUtil restTemplateUtil;

    @Autowired
    private HttpSession session;

    @GetMapping("transportparam")
    public void getParamFromIndex(HttpServletRequest request, HttpServletResponse response){
        response.setContentType("text/html;charset=gb2312");
        Map<String, String[]> parameterMap = request.getParameterMap();
        HttpSession session = request.getSession();

        for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            session.setAttribute(entry.getKey(), entry.getValue()[0]);
        }
    }

    @GetMapping("demonstration")
    public ModelAndView showDemonstration(HttpServletRequest request, Map<String, Object> map){

        ModelAndView word = new ModelAndView("Demonstration");

        Map<String, Object> loginParam = new HashMap<>();
        loginParam.put("client_id", "szgt_client_id");
        loginParam.put("client_secret", "szgt123456");
        loginParam.put("grant_type", "password");
        loginParam.put("password", "admin");
        loginParam.put("scope", "szgtId");
        loginParam.put("username", "admin");

        String access_token = RestTemplateUtil.getToken();
        access_token = "Bearer " + access_token;

        String url = "http://10.238.222.67:8238/contract/attachment/query?PropertyID={PropertyID}";
        Map<String, Object> params = new HashMap<>();
        params.put("PropertyID", "pageofficetest");

        String result = null;
        try{
             result = restTemplateUtil.getRequest(url, params, access_token);
        }catch (Exception e){
            e.printStackTrace();
            return word;
        }

        DataResult<List<AttachmentResultVo>> o = null;
        try {
            o = objectMapper.readValue(result, new TypeReference<DataResult<List<AttachmentResultVo>>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<AttachmentResultVo> data = o.getData();
        map.put("attalist", data);
        map.put("accesstoken", access_token);


        return word;

//        ModelAndView mv = new ModelAndView("Demonstration");
//        return mv;
    }

    @GetMapping("demo")
    public ModelAndView showDemo(HttpServletRequest request, Map<String, Object> map){

        ModelAndView word = new ModelAndView("Demo");

        map.put("access_token", RestTemplateUtil.getToken());

        return word;

    }


}
