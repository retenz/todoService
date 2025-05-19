package com.shortcut.todo.advice;

import com.shortcut.todo.entity.ExceptionResponse;
import com.shortcut.todo.exception.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ExceptionResponse> serviceExceptionHandler(ServiceException exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(exception.getMessage());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
