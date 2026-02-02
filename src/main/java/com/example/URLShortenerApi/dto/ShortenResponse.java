package com.example.URLShortenerApi.dto;

import java.time.LocalDateTime;

public class ShortenResponse {
    private String shortUrl;
    private String originalUrl;
    private LocalDateTime expiresAt;

    public ShortenResponse(String shortUrl, String originalUrl, LocalDateTime expiresAt) {
        this.shortUrl = shortUrl;
        this.originalUrl = originalUrl;
        this.expiresAt = expiresAt;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
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
