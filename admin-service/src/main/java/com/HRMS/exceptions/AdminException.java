package com.HRMS.exceptions;

import lombok.Getter;

@Getter
public class AdminException extends RuntimeException{
    private final ErrorType errorType;
    public AdminException(ErrorType errorType){
        super(errorType.getMessage());
        this.errorType = errorType;
    }

    public AdminException(ErrorType errorType, String message){
        super(message);
        this.errorType = errorType;
    }


}
