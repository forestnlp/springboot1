package com.example.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice(basePackages = {"com.example",})
public class GlobalDefaultExceptionHandler {

    @ExceptionHandler({BusinessException.class})
    @ResponseBody
    public ErrorInfo defaultErrorHandler(HttpServletRequest request, HttpServletResponse response) throws Exception {

        ErrorInfo errorInfo = new ErrorInfo();
        errorInfo.setMessage(errorInfo.getMessage());
        errorInfo.setUrl(request.getRequestURI());
        errorInfo.setCode(ErrorInfo.SUCCESS);
        return errorInfo;
    }

}
