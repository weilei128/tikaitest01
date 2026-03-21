package com.pcitc.szgt.contract.exception;

import com.pcitc.szgt.contract.common.DataResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public DataResult handleBaseException(BaseException baseException) {
        baseException.printStackTrace();
        return DataResult.fail(null, baseException.getCode(), baseException.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public DataResult handleException(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        log.error(sw.toString());

        return DataResult.fail(null, 500, e.getMessage());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public String handleConstraintViolation(ConstraintViolationException ex){
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();

        for(ConstraintViolation cv : constraintViolations){
            Path propertyPath = cv.getPropertyPath();
            String msg = cv.getMessage();
            String message = String.format("%s %s", propertyPath, msg);
            stringBuilder.append(message).append("\r\n");
        }

        return stringBuilder.toString();
    }
}
