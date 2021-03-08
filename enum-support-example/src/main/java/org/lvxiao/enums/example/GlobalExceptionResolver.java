package org.lvxiao.enums.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionResolver {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public String validatorExceptionHandler(MethodArgumentNotValidException e) {
        StringBuilder errorMessage = new StringBuilder();
        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> errors = bindingResult.getFieldErrors();
        errors.forEach(error -> errorMessage.append(error.getDefaultMessage()).append(";"));
        String err = errorMessage.toString();
        log.info("数据检验失败, err: {}", err);
        return err;
    }
}
