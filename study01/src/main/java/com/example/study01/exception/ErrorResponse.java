package com.example.study01.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponse{
    private final int status;
    private final String errorCode;
    private final String errorMessage;

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .errorCode(errorCode.getHttpStatus().name())
                        .errorMessage(errorCode.getErrorMessage())
                        .build());
    }
}
