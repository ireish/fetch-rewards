package com.example.ireish.fetchrewards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, List<Map.Entry<String, String>>> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, List<Map.Entry<String, String>>> map =  new HashMap<>();
        List<Map.Entry<String, String>> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.add(Map.entry(fieldError.getField(), fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Bad Request"));
        });
        map.put("errors", errors);
        return map;
    }
}
