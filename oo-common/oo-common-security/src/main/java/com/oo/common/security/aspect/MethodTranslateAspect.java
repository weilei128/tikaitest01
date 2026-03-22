package com.oo.common.security.aspect;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.oo.common.core.utils.ServletUtils;
import com.oo.common.core.utils.StringUtils;
import com.oo.common.core.web.page.TableDataInfo;
import com.oo.common.core.annotation.MethodTranslate;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;

/**
 * 调用方法的数据翻译处理
 *
 * @author
 */
@Aspect
@Component
public class MethodTranslateAspect {

    @Resource
    private ReflectUtil reflectUtil;

    @Pointcut(value = "@annotation(translate)", argNames = "translate")
    public void doTranslate(MethodTranslate translate) {
    }

    @AfterReturning(pointcut = "doTranslate(translate)", returning = "result", argNames = "point,result,translate")
    public Object translation(final JoinPoint point,  Object result, MethodTranslate translate) throws Throwable {
        Signature signature = point.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        StringBuilder aa= new StringBuilder("aa");

        final Class<?> aClass = translate.dtoClass();

        final boolean isCollection = result instanceof Collection;
        final boolean isPage = result instanceof TableDataInfo;
        final boolean isTargetClass = aClass.equals(result.getClass());

        //获取请求头的语言标识
        HttpServletRequest httpServletRequest = ServletUtils.getRequest();
        String lang = "";
        if (StringUtils.isNotNull(httpServletRequest)) {
            lang = httpServletRequest.getHeader("Accept-Language");
        }
        if (StringUtils.isEmpty(lang)) {
            lang = "zh-cn";
        }

        if (!isCollection && !isPage && !isTargetClass) return result;
        else if (isCollection) {
            List<Object> list = (List<Object>) result;
            if (CollectionUtils.isEmpty(list)) return result;
            for (Object row : list) reflectUtil.translateDTO(row, aClass, lang);
        } else if (isPage) {
            TableDataInfo page = (TableDataInfo) result;
            if (CollectionUtils.isEmpty(page.getRows())) return result;
            final List<Object> records = (List<Object>) page.getRows();
            for (Object record : records) reflectUtil.translateDTO(record, aClass, lang);
        } else {
            reflectUtil.translateDTO(result, aClass, lang);
        }

        return result;
    }
}
