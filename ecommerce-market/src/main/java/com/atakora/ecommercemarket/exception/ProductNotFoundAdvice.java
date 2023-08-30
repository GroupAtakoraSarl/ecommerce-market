package com.atakora.ecommercemarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class ProductNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(ProductNotFoundException exception){
        Map<String,String> errorMap= new HashMap<>();
        errorMap.put("error message", exception.getMessage());
        return errorMap;
    }
}
