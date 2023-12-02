package com.knu.bobmate.Reservation.Exception;

import com.knu.bobmate.Reservation.Exception.Exceptions.ReservationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReservationExceptionHandler {
    @ExceptionHandler(ReservationException.class)
    public ResponseEntity<String> loginException(ReservationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
