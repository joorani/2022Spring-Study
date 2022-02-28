package com.example.study01.exception;

import lombok.Data;

@Data
public class SearchNotFoundException extends RuntimeException{

    private ErrorCode errorCode;

    public SearchNotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
