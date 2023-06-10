package com.example.demo.movie.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import java.net.ConnectException;

@RestControllerAdvice
public class RentalControllerAdvice {
    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleNotFoundException(HttpClientErrorException.NotFound exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleIncorrectDataException(HttpClientErrorException.BadRequest exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ResponseEntity<String> handleBadGatewayException(HttpServerErrorException.InternalServerError exception) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }

    @ExceptionHandler(ConnectException.class)
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public ResponseEntity<String> handleGatewayTimeoutException(ConnectException exception) {
        return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                .body("Exception occurred on request. Exception message: " + exception.getLocalizedMessage());
    }
}
