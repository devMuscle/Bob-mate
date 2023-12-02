package com.knu.bobmate.Account.Exception;

import com.knu.bobmate.Account.Controller.AccountController;
import com.knu.bobmate.Account.Exception.Exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AccountExceptionHandler {
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> loginException(LoginException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
