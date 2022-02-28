package com.example.study01.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {SearchNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleSearchNotFoundException(SearchNotFoundException e) {

        return ErrorResponse.toResponseEntity(ErrorCode.NOT_FOUND_MOVIES);
    }


}
