package com.nttdata.msafiliados.domain.exception;

public class ApiRequestException extends RuntimeException{

    private String message;

    public ApiRequestException(String message){
        super(message);
    }

}
