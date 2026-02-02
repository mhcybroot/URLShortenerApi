package com.example.URLShortenerApi.controller;

import com.example.URLShortenerApi.dto.OriginalUrlResponse;
import com.example.URLShortenerApi.dto.ShortenRequest;
import com.example.URLShortenerApi.dto.ShortenResponse;
import com.example.URLShortenerApi.model.UrlMapping;
import com.example.URLShortenerApi.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UrlShortenerController {

    private final UrlShortenerService service;

    public UrlShortenerController(UrlShortenerService service) {
        this.service = service;
    }

    @PostMapping("/api/shorten")
    public ResponseEntity<ShortenResponse> shortenUrl(@RequestBody ShortenRequest request,
            HttpServletRequest servletRequest) {
        if (request.getOriginalUrl() == null || request.getOriginalUrl().isEmpty()) {
            throw new IllegalArgumentException("Original URL is required");
        }
        if (request.getValidity() == null) {
            throw new IllegalArgumentException("Validity date is required");
        }

        UrlMapping mapping = service.shortenUrl(request.getOriginalUrl(), request.getValidity());

        String baseUrl = ServletUriComponentsBuilder.fromRequestUri(servletRequest)
                .replacePath(null)
                .build()
                .toUriString();
        String fullShortUrl = baseUrl + "/r/" + mapping.getShortCode();

        return ResponseEntity.ok(new ShortenResponse(
                fullShortUrl,
                mapping.getOriginalUrl(),
                mapping.getExpiresAt()));
    }

    @GetMapping("/r/{shortCode}")
    public ResponseEntity<OriginalUrlResponse> getOriginalUrl(@PathVariable String shortCode) {
        UrlMapping mapping = service.getOriginalUrl(shortCode);
        return ResponseEntity.ok(new OriginalUrlResponse(
                mapping.getOriginalUrl(),
                mapping.getExpiresAt()));
    }
}
