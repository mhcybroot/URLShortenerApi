package com.example.URLShortenerApi.dto;

import java.time.LocalDateTime;

public class OriginalUrlResponse {
    private String originalUrl;
    private LocalDateTime expiresAt;

    public OriginalUrlResponse(String originalUrl, LocalDateTime expiresAt) {
        this.originalUrl = originalUrl;
        this.expiresAt = expiresAt;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
}
