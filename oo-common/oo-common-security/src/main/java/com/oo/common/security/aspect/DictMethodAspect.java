package com.oo.common.security.aspect;

import com.oo.common.core.annotation.DictMethod;
import com.oo.common.core.utils.ServletUtils;
import com.oo.common.core.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


/**
 * 方法类字典自动添加
 *
 * @author
 */
@Aspect
@Component
public class DictMethodAspect {

    @Resource
    private ReflectUtil reflectUtil;

    @Before("@annotation(dictMethod)")
    public void doBefore(JoinPoint point, DictMethod dictMethod) throws Throwable
    {
        getDictDataId(point, dictMethod);
    }

    public void getDictDataId(final JoinPoint point, DictMethod dictMethod) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        //获取请求头的语言标识
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        String lang = "";
        if (StringUtils.isNotNull(httpServletRequest)) {
            lang = httpServletRequest.getHeader("Accept-Language");
        }
        if (StringUtils.isEmpty(lang)) {
            lang = "zh-cn";
        }

        final Class<?> aClass = dictMethod.dtoClass();
//        Object[] obj = point.getArgs();
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof Collection) {
                reflectUtil.submitDictData((List<Object>) arg, aClass, lang);
            }
        }
    }
}
