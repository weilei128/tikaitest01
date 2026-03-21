package com.pcitc.legalAffairs.service.pageoffice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.pcitc.common.entity.ResultCode;
import com.pcitc.common.entity.SysUserInfo;
import com.pcitc.common.exception.BaseException;
import com.pcitc.legalAffairs.config.UrlConfig;
import com.zhuozhengsoft.pageoffice.DocumentVersion;
import com.zhuozhengsoft.pageoffice.OpenModeType;
import com.zhuozhengsoft.pageoffice.PageOfficeCtrl;

/**
 * PageOffice相关服务
 * @author HoLiX
 *
 */
@Service
public class PageOfficeService {
	
	@Autowired
	private UrlConfig urlConfig;

    /**
     * 将文件缓存到本地, 直接打开网络文件将可能无法正常保存
     * 必须传入token
     *
     * @param path
     * @param fileName
     * @param token
     * @return
     * @throws IOException
     */
    public String bufferRemoteFile(String path, String fileName, String token) throws IOException {
        if (fileName == null || fileName.equals("")) {
            String[] strings = path.split("/");
            fileName = strings[strings.length - 1];
        }
        String tempPath = "." + File.separator + "_temp" + File.separator + System.currentTimeMillis() + File.separator;
        File dir = new File(tempPath);
        dir.mkdirs();
        tempPath = dir.getCanonicalPath();
        File file = new File(tempPath, fileName);

        path = urlConfig.getFileStorage() + path + "?access_token=" + token;
        try (
                InputStream is = new URL(path).openStream();
                FileOutputStream os = new FileOutputStream(file);
        ) {
            int length = -1;
            byte[] buffer = new byte[1024];
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

            System.out.println(file.getPath());
            return file.getPath();
        } catch (IOException e) {
            e.printStackTrace();
            throw new BaseException(ResultCode.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * 在线打开文件
     */
    public String openFile(Map<String, String> map, SysUserInfo userinfo, HttpServletRequest request, Model model) throws IOException {
    	////////////
    	Long start = System.currentTimeMillis();
    	System.out.printf("进入PageOffice: %d", start);
    	System.out.println();
    	////////////
        PageOfficeCtrl ctrl = new PageOfficeCtrl(request);
        if (userinfo == null) {
            userinfo = new SysUserInfo();
            userinfo.setfCname("guest");
        }
//        String savePage = map.get("savePage");
        // 保存功能调用的路径, 可以使用savePage参数自定义到其他路径
//        if (savePage == null || "".equals(savePage)) {
//            savePage = "saveFile";
//        }
        // 保存功能调用的路径, 可以使用savePage参数自定义到其他路径
//        StringBuilder saveFilePage = new StringBuilder(savePage).append("?");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            saveFilePage.append(entry.getKey()).append("=").append(URLEncoder.encode(Optional.ofNullable(entry.getValue()).orElse(""), "UTF-8")).append("&");
//        }
        String path = map.get("path");
//        String id = map.get("id");
        String fileName = map.get("fileName");
        String token = map.get("access_token");
        // 是否只读
        boolean readonly = "1".equals(map.get("readonly"));
        ctrl.setServerPage(urlConfig.getLawUrl() + "poserver.zz");
        // 非只读状态
        if (!readonly) {
            // PageOffice窗口上的保存按钮
            ctrl.addCustomToolButton("保存", "Save", 1);
            // PageOffice窗口添加另存为按钮
            ctrl.addCustomToolButton("另存为", "SaveAs();", 11);

//            ctrl.setSaveFilePage(saveFilePage.toString());
        }

        // 关闭PageOffice窗口时调用删除缓存文件接口
        ctrl.setJsFunction_AfterDocumentClosed("closeCallback()");
        ctrl.setJsFunction_AfterDocumentSaved("CloseWin()");
        Long buffered = start;
        if (path == null || path.length() == 0) {
            ctrl.webCreateNew(userinfo.getfCname(), DocumentVersion.Word2007);
        } else {
        	////////////
        	Long buffer = System.currentTimeMillis();
        	System.out.printf("进入下载缓存文件: %d", buffer);
        	System.out.println();
        	System.out.printf("用时: %d ms", buffer - start);
        	System.out.println();
        	////////////
            String tempFile = bufferRemoteFile(path, fileName, token);
            ////////////
          	buffered = System.currentTimeMillis();
          	System.out.printf("完成下载缓存文件: %d", buffered);
          	System.out.println();
          	System.out.printf("用时: %d ms", buffered - buffer);
          	System.out.println();
          	////////////
            // 不加file:// PageOffice会将Linux文件目录结构当成URL
            ctrl.webOpen("file://" + tempFile, checkOpenModeType(path, readonly), userinfo.getfCname());

            String[] split = tempFile.split(File.separator.replace("\\", "\\\\"));
            map.put("tempFile", split[split.length - 2]);
        }

        ctrl.setTagId("PageOfficeCtrl1");
        map.put("pageoffice", ctrl.getHtmlCode("PageOfficeCtrl1"));
        model.addAllAttributes(map);
        ////////////
      	Long end = System.currentTimeMillis();
      	System.out.printf("离开PageOffice后台接口: %d", end);
      	System.out.println();
      	System.out.printf("用时: %d ms", end - buffered);
      	System.out.println();
      	System.out.printf("总用时: %d ms", end - start);
      	System.out.println();
      	////////////
        return "pageoffice/showFile";
    }

    public OpenModeType checkOpenModeType(String fileName, boolean readonly) {
    	String suffix = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
    	switch (suffix) {
    	case ".doc": 
    	case ".docx":
    	case ".docm":
    	case ".rtf":
    	case ".wps": 
    		if (!readonly) {
                return OpenModeType.docRevisionOnly;
            } else {
                return OpenModeType.docReadOnly;
            }
    		// Returned
    	case ".xls":
    	case ".xlsx":
    	case ".xlsm":
    	case ".et":
    		if (!readonly) {
                return OpenModeType.xlsNormalEdit;
            } else {
                return OpenModeType.xlsReadOnly;
            }
    		// Returned
    	case ".ppt":
    	case ".pptx":
    		if (!readonly) {
                return OpenModeType.pptNormalEdit;
            } else {
                return OpenModeType.pptReadOnly;
            }
    		// Returned
    	case ".vsd":
    		if (!readonly) {
                return OpenModeType.vsdNormalEdit;
            } else {
                return OpenModeType.vsdNormalEdit;
            }
    		// Returned
    	case ".mpp":
    		return OpenModeType.mppNormalEdit;
    		// Returned
    	default:
    		if (!readonly) {
                return OpenModeType.docRevisionOnly;
            } else {
                return OpenModeType.docReadOnly;
            }
    	}
    }


}
