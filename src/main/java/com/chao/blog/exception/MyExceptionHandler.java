package com.chao.blog.exception;

import com.chao.blog.entity.ResultCode;
import com.chao.blog.entity.ResultInfo;
import com.chao.blog.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@ControllerAdvice
public class MyExceptionHandler {

    Logger logger = Logger.getLogger("Exception");

    @ExceptionHandler
    public void handleImageException(ImageException exception) {
        logger.warning(exception.getMessage());
    }

    @ExceptionHandler
    @ResponseBody
    public ResultInfo<Set<String>> handleRequestArgError(MethodArgumentNotValidException exception) {
        Set<String> errors = new HashSet<>();
        List<ObjectError> allErrors = exception.getAllErrors();
        for (ObjectError e : allErrors) {
            errors.add(e.getDefaultMessage());
        }
        return ResultUtil.build(ResultCode.PARAM_ERROR, errors);
    }
}
