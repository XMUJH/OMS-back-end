package com.wedo.OMS.handler;

import com.wedo.OMS.exception.PasswordIncorrectException;
import com.wedo.OMS.exception.UserNotFoundException;
import com.wedo.OMS.viewmodel.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UserNotFoundException.class, PasswordIncorrectException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<Error> userNotFoundHandler() {
        Error error = new Error(403, "用户名或密码错误");
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
