package com.pcitc.szgt.aspect;

import com.alibaba.fastjson.JSON;
import com.pcitc.common.entity.Result;
import com.pcitc.common.entity.SysUserInfo;
import com.pctic.common.utils.UserUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@Aspect
public class ControllerLogAspect {

	@Pointcut("execution(public * com.pcitc..controller..*Controller.*(..))")
	public void controllerMethod() {}

	@Before("controllerMethod()")
	public void logRequestInfo(JoinPoint joinPoint) {
		try {
			log.info("============================REQUEST============================");
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
			if (attributes != null) {
				HttpServletRequest request = attributes.getRequest();

				Signature signature = joinPoint.getSignature();
				log.info("请求信息：URL = {{}}", request.getRequestURI());
				log.info("请求方式 = {{}}", request.getMethod());
				log.info("请求IP = {{}}", request.getRemoteAddr());
				SysUserInfo userInfo = UserUtils.getUserInfo();
				if (userInfo != null) {
					log.info("请求人 = {id = {}, account = {}, name = {}}", userInfo.getfId(), userInfo.getfAccount(), userInfo.getfCname());
				} else {
					log.info("请求人 = 游客");
				}
				log.info("请求类 = {{}}", signature.getDeclaringTypeName());
				String[] paramNames = ((MethodSignature) signature).getParameterNames();
				Object[] paramValues = joinPoint.getArgs();

				if (paramNames == null || paramNames.length == 0) {
					log.info("请求参数: {}");
				} else {
					Map<String, Object> map = new HashMap<>();
					for (int i = 0; i < paramNames.length; i++) {
						int finalI = i;
						if (!(paramValues[finalI] instanceof HttpServletResponse)) {
							map.computeIfAbsent(paramNames[i], k -> paramValues[finalI]);
						}
					}
					log.info("请求参数: {}", JSON.toJSONString(map));
	//				log.info(requestLog.toString());

				}
				log.info("==========================REQUEST END==========================");
			}
		} catch (Throwable e) {
			log.error("ERROR: ", e);
		}
	}

	/**
	 * 方法执行后
	 *
	 */
	@AfterReturning(returning = "result", pointcut = "controllerMethod()")
	public void logResultVOInfo(Result result) {
		try {
			log.info("============================RESPONSE============================");

			log.info("请求响应：" + JSON.toJSONString(result));

			log.info("==========================RESPONSE END==========================");
		} catch (Throwable e) {
			log.error("ERROR: ", e);
		}
	}


}
