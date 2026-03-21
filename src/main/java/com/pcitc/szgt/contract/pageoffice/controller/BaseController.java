package com.pcitc.szgt.contract.pageoffice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pcitc.szgt.contract.common.DataResult;
import com.pcitc.szgt.contract.config.attachment.AttachmentConfig;
import com.pcitc.szgt.contract.config.oauthconfig.UserInfo;
import com.pcitc.szgt.contract.exception.BaseException;
import com.pcitc.szgt.contract.util.CurrentUserUtil;
import com.pcitc.szgt.contract.util.RedisUtils;
import com.pcitc.szgt.contract.util.RestTemplateUtil;
import com.pcitc.szgt.contract.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BaseController {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private CurrentUserUtil currentUserUtil;

    @Autowired
    private AttachmentConfig attachmentConfig;

    /**
     * 获取文件输入流
     * @return
     */
    private static InputStream getInputStream(String fileurl){
        InputStream inputStream = null;
        HttpURLConnection httpURLConnection = null;
        try {
            URL url = new URL(fileurl);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestMethod("GET");
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode == 200) {
                inputStream = httpURLConnection.getInputStream();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    /**
     * 获取远程文件
     * @param fileurl
     * @param response
     * @return
     */
    @GetMapping("getFile")
    public void getFile(String fileurl, HttpServletResponse response){
        int indexOf = fileurl.indexOf("/group");
        String substring = fileurl.substring(indexOf);

        String resurl = "http://" + attachmentConfig.toIpString() + substring;

        ServletOutputStream outputStream = null;
        InputStream decryptInputStream = getInputStream(resurl);
        try{
            response.reset();
            outputStream = response.getOutputStream();
            // 在http响应中输出流
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = decryptInputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("getFile2")
    public void getPgActive(String fileurl, HttpServletResponse response) {
        ServletOutputStream outputStream = null;
        InputStream inputStream = null;
        try{
            Resource resource = new ClassPathResource("static/testword.docx");
            inputStream = resource.getInputStream();
            outputStream = response.getOutputStream();
            byte[] cache = new byte[1024];
            int nRead = 0;
            while ((nRead = inputStream.read(cache)) != -1) {
                outputStream.write(cache, 0, nRead);
                outputStream.flush();
            }
            outputStream.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @RequestMapping(value="/index", method= RequestMethod.GET)
    public ModelAndView showIndex(){
        ModelAndView mv = new ModelAndView("Index");
        return mv;
    }

    @PostMapping("transportparam")
    public DataResult<String> getParamFromIndex(HttpServletRequest request, HttpServletResponse response){
//        Map<String, String[]> parameterMap = request.getParameterMap();
//        HttpSession session = request.getSession();
//
//        for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
//            System.out.println(entry.getKey() + "---" + entry.getValue()[0]);
//            session.setAttribute(entry.getKey(), entry.getValue()[0]);
//        }

        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, String> params = new HashMap<>();
        for(Map.Entry<String, String[]> entry:parameterMap.entrySet()){
            params.put(entry.getKey(), entry.getValue()[0]);
        }

        String strParams = "{}";
        try {
            strParams = objectMapper.writeValueAsString(params);
        } catch (JsonProcessingException e) {
            throw new BaseException("pageoffice transportparam fail", 500);
        }

        String requestUUID = UUIDUtils.getUUID();
        redisUtils.strset("contract_pageoffice" + requestUUID, strParams, 500000);

        return DataResult.success(requestUUID);
    }
}
