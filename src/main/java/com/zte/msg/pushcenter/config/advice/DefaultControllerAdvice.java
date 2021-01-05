package com.zte.msg.pushcenter.config.advice;

import com.zte.msg.pushcenter.dto.BaseResponse;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 15:20
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class, Controller.class})
public class DefaultControllerAdvice {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(value = CommonException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handleCommonException(CommonException e) {
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage(e.getMessage(),
                null, locale);
        log.error(message);
        return new BaseResponse().code(e.getCode()).message(message);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class, BindException.class})
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handMethodArgumentNotValidAndBindException(Exception e) {
        FieldError fieldError = null;
        String defaultMessage = "";
        if (e instanceof BindException) {
            BindException e1 = (BindException) e;
            fieldError = e1.getBindingResult().getFieldError();
            defaultMessage = Objects.requireNonNull(fieldError).getDefaultMessage();
        }
        Locale locale = LocaleContextHolder.getLocale();
        if (null != defaultMessage) {
            Integer code = Integer.valueOf(defaultMessage);
            Object[] arguments = fieldError.getArguments();
            String field = fieldError.getField();
            if (null != arguments) {
                String message = null;
                if (arguments.length >= 3) {
                    message = messageSource.getMessage(ErrorCode.messageOf(code),
                            new Object[]{field, arguments[2], arguments[1]}, locale);
                }
                if (arguments.length == 2) {
                    message = messageSource.getMessage(ErrorCode.messageOf(code),
                            new Object[]{field, arguments[1]}, locale);
                }
                if (arguments.length == 1) {
                    message = messageSource.getMessage(ErrorCode.messageOf(code),
                            new Object[]{field}, locale);
                }
                log.error(message);
                return new BaseResponse().code(code).message(message);
            }
        }
        return null;
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse handMethodArgumentNotValidAndBindException(ConstraintViolationException e) {
        Locale locale = LocaleContextHolder.getLocale();
        ConstraintViolation<?> violation = e.getConstraintViolations().stream().findFirst().get();
        Integer code = Integer.valueOf(violation.getMessageTemplate());
        String field = e.getMessage().split(":")[0].split("\\.")[1];
        String message = messageSource.getMessage(ErrorCode.messageOf(code),
                new Object[]{field}, locale);
        return new BaseResponse().code(code).message(message);
    }
}
