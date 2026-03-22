package com.oo.common.log.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.oo.common.core.utils.ServletUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.utils.ip.IpUtils;
import com.oo.common.log.annotation.FileLog;
import com.oo.common.log.enums.BusinessStatus;
import com.oo.common.log.enums.FileOperType;
import com.oo.common.log.service.AsyncLogService;
import com.oo.common.security.utils.SecurityUtils;
import com.oo.system.api.domain.ReportFileLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 文件操作日志记录处理
 *
 * @author ruoyi
 */
@Aspect
@Component
public class FileLogAspect
{
    private static final Logger log = LoggerFactory.getLogger(FileLog.class);

    @Autowired
    private AsyncLogService asyncLogService;

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning(pointcut = "@annotation(controllerLog)", returning = "jsonResult")
    public void doAfterReturning(JoinPoint joinPoint, FileLog controllerLog, Object jsonResult)
    {
        handleLog(joinPoint, controllerLog, null, jsonResult);
    }

    /**
     * 拦截异常操作
     *
     * @param joinPoint 切点
     * @param e 异常
     */
    @AfterThrowing(value = "@annotation(controllerLog)", throwing = "e")
    public void doAfterThrowing(JoinPoint joinPoint, FileLog controllerLog, Exception e)
    {
        handleLog(joinPoint, controllerLog, e, null);
    }

    protected void handleLog(final JoinPoint joinPoint, FileLog controllerLog, final Exception e, Object jsonResult)
    {
        try
        {
            ReportFileLog reportFileLog = new ReportFileLog();
            reportFileLog.setStatus(String.valueOf(BusinessStatus.SUCCESS.ordinal()));
            // 请求的地址
            String ip = IpUtils.getIpAddr(ServletUtils.getRequest());
            reportFileLog.setOperIp(ip);
            String username = SecurityUtils.getUsername();
            if (StringUtils.isNotBlank(username))
            {
                reportFileLog.setOperName(username);
            }
            reportFileLog.setOperTime(new Date());
            if (e != null)
            {
                reportFileLog.setStatus(String.valueOf(BusinessStatus.FAIL.ordinal()));
                reportFileLog.setErrorMsg(StringUtils.substring(e.getMessage(), 0, 2000));
            }
            // 设置操作类型
            reportFileLog.setOperType(String.valueOf(controllerLog.fileOperType().ordinal()));
            // 设置日志内容
            reportFileLog.setContent(reportFileLog.getOperType());
            // 处理设置注解上的参数 请求返回参数
            getControllerMethodDescription(joinPoint, controllerLog, reportFileLog, jsonResult);
        }
        catch (Exception exp)
        {
            // 记录本地异常日志
            log.error("==前置通知异常==");
            log.error("异常信息:{}", exp.getMessage());
            exp.printStackTrace();
        }
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param fileLog 日志
     * @param reportFileLog 操作日志
     * @throws Exception
     */
    public void getControllerMethodDescription(JoinPoint joinPoint, FileLog fileLog, ReportFileLog reportFileLog, Object jsonResult) throws Exception
    {
        // 获取请求参数
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            try {
                Object arg = args[fileLog.argIndex()];
                if (arg instanceof Collection) { //批量操作
                    if (fileLog.fileOperType().equals(FileOperType.UPLOAD)) {
                        batchUploadLog((List<Object>) arg, reportFileLog, jsonResult);
                    }
                    else {
                        batchIdLog((List<Object>) arg, reportFileLog, jsonResult);
                    }
                }
                else if (arg instanceof String) {
                    singleIdLog((String) arg, reportFileLog, jsonResult);
                }
            }
            catch (Exception e) {
                log.error("==获取request参数异常==");
                e.printStackTrace();
            }
        }
//        // 获取参数的信息，传入到数据库中。
//        setRequestValue(joinPoint, reportFileLog, fileLog, jsonResult);
    }

//    /**
//     * 获取请求的参数，放到log中
//     *
//     * @param reportFileLog 操作日志
//     * @throws Exception 异常
//     */
//    private void setRequestValue(JoinPoint joinPoint, ReportFileLog reportFileLog, FileLog fileLog, Object jsonResult) throws Exception
//    {
//        Object[] args = joinPoint.getArgs();
//        if (args != null && args.length > 0) {
//            try {
//                Object arg = args[fileLog.argIndex()];
//                if (arg instanceof Collection) { //批量操作
//                    if (fileLog.fileOperType().equals(FileOperType.UPLOAD)) {
//                        batchSubmitLog((List<Object>) arg, reportFileLog, jsonResult);
//                    }
//                }
//            }
//            catch (Exception e) {
//                log.error("==获取request参数异常==");
//                e.printStackTrace();
//            }
//        }
//    }

    private void batchUploadLog(List<Object> list, ReportFileLog reportFileLog, Object jsonResult) {
        Long batchId = IdWorker.getId();
        if (list.size() > 1) { //批量
            reportFileLog.setOperType(String.valueOf(FileOperType.BATCH_UPLOAD.ordinal()));
            reportFileLog.setContent(reportFileLog.getOperType());
        }
        // 获取response，参数和值
        JSONObject responseJson = JSONObject.parseObject(JSON.toJSONString(jsonResult));
//        System.out.println(responseJson);
        JSONArray dataArray = null;
        if (responseJson != null && responseJson.get("code") != null && Integer.parseInt(responseJson.get("code").toString()) == 200) {
            dataArray = responseJson.getJSONArray("data");
        }
        int i = 0;
        for (Object item : list) {
            ReportFileLog fileLog = new ReportFileLog();
            BeanUtils.copyProperties(reportFileLog, fileLog);
            fileLog.setBatchId(batchId);
            JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(item));
            if (jsonObject.get("fileName") != null) {
                fileLog.setFileName(jsonObject.get("fileName").toString());
            }
            if (jsonObject.get("wellName") != null) {
                fileLog.setWellName(jsonObject.get("wellName").toString());
            }
            if (jsonObject.get("organizationName") != null) {
                fileLog.setOrganizationName(jsonObject.get("organizationName").toString());
            }
            if (jsonObject.get("fileVersion") != null) {
                fileLog.setFileVersion(jsonObject.get("fileVersion").toString());
            }
            if (dataArray != null ) {
                JSONObject obj = (JSONObject) dataArray.get(i);
                if (obj != null && obj.get("fileId") != null) {
                    fileLog.setFileId(obj.get("fileId").toString());
                }
            }
            asyncLogService.saveFileLog(fileLog);
//            User user = JSON.parseObject(JSON.toJSONString(object), ReportFiles.class);
            i++;
        }
    }

    private void batchIdLog(List<Object> list, ReportFileLog reportFileLog, Object jsonResult) {
        Long batchId = IdWorker.getId();
        if (list.size() > 1) { //批量
            reportFileLog.setOperType(String.valueOf(Integer.parseInt(reportFileLog.getOperType()) + 1));
            reportFileLog.setContent(reportFileLog.getOperType());
        }
    }

    private void singleIdLog(String fileId, ReportFileLog reportFileLog, Object jsonResult) {
        Long batchId = IdWorker.getId();
        reportFileLog.setBatchId(batchId);

    }
}
