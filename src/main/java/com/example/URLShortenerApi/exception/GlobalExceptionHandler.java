package com.example.URLShortenerApi.exception;

import com.example.URLShortenerApi.service.UrlShortenerService.UrlExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UrlExpiredException.class)
    public ResponseEntity<String> handleUrlExpiredException(UrlExpiredException ex) {
        System.out.println("Handling UrlExpiredException: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.GONE)
                .contentType(org.springframework.http.MediaType.TEXT_PLAIN)
                .body(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
