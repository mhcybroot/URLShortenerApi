package com.example.URLShortenerApi.dto;

import java.time.LocalDateTime;

public class ShortenRequest {
    private String originalUrl;
    private LocalDateTime validity;

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }
}
