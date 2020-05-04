package com.example.shop.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import static java.util.stream.Collectors.toList;

@ControllerAdvice(annotations = RestController.class)
public class BindingExceptionHandler {

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleBindException(BindException ex) {
        var bindingResult = ex.getBindingResult();

        var apiFieldErrors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());

        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }
}
