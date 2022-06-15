package com.nttdata.msafiliados.business.controller;

import com.nttdata.msafiliados.domain.exception.ApiException;
import com.nttdata.msafiliados.domain.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<ApiException> handleApiRequestException(ApiRequestException exception){
        ApiException apiException = new ApiException(exception.getMessage());
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

}
