package com.example.demologging.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/")
public class LoggingController {

    @Value("${custom.env.value1}")
    private int value1;

    @Value("${custom.env.value2}")
    private int value2;

    @GetMapping("/")
    public ResponseEntity<String> welcome() {
        log.info("Received request for welcome message");
        return ResponseEntity.ok("Welcome to the Spring Boot Application!");
    }

    @GetMapping("/exp")
    public ResponseEntity<String> calculateExponent() {
        log.debug("Starting exponent calculation");
        int result = (int) Math.pow(value1, value2);
        log.debug("Finished exponent calculation: {}^{} = {}", value1, value2, result);
        return ResponseEntity.ok("The result of " + value1 + " raised to the power of " + value2 + " is " + result);
    }

    @GetMapping("/get-errors")
    public ResponseEntity<String> getErrors() {
        log.error("Custom error endpoint was called");
        throw new CustomException("This is a custom error");
    }

    @Slf4j
    public static class CustomException extends RuntimeException {
        public CustomException(String message) {
            super(message);
            log.error(message);
        }
    }

}