package com.demo.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Handle Exception is a class to handle exceptions globally .
 */
@ControllerAdvice
public final class HandleException {

    /**
     * To handle specific exceptions and sending custom responses to the client.
     *
     * @param exception instance of ItemNotFoundException.
     * @return HTTP response
     */
    @ExceptionHandler(value = ItemNotFoundException.class)
    public ResponseEntity<Object> exception(ItemNotFoundException exception) {
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }
}
