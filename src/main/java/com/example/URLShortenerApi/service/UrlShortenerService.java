package com.example.URLShortenerApi.service;

import com.example.URLShortenerApi.model.UrlMapping;
import com.example.URLShortenerApi.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository repository;
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_CODE_LENGTH = 6;
    private final Random random = new Random();

    public UrlShortenerService(UrlMappingRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public UrlMapping shortenUrl(String originalUrl, LocalDateTime validity) {
        // 1. Deduplication: Check if URL already exists
        Optional<UrlMapping> existingMapping = repository.findByOriginalUrl(originalUrl);
        if (existingMapping.isPresent()) {
            UrlMapping mapping = existingMapping.get();
            // Extend validity if the new requested validity is further in the future
            if (validity.isAfter(mapping.getExpiresAt())) {
                mapping.setExpiresAt(validity);
                return repository.save(mapping);
            }
            return mapping;
        }

        // 2. Generate unique short code
        String shortCode;
        do {
            shortCode = generateShortCode();
        } while (repository.findByShortCode(shortCode).isPresent());

        // 3. Save new mapping
        UrlMapping newMapping = new UrlMapping(originalUrl, shortCode, validity);
        return repository.save(newMapping);
    }

    public UrlMapping getOriginalUrl(String shortCode) {
        // 1. Find mapping
        UrlMapping mapping = repository.findByShortCode(shortCode)
                .orElseThrow(() -> new IllegalArgumentException("URL not found")); // Using IllegalArgument for now,
                                                                                   // will handle exception later

        // 2. Check expiration
        if (mapping.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new UrlExpiredException("url expired");
        }

        return mapping;
    }

    private String generateShortCode() {
        StringBuilder sb = new StringBuilder(SHORT_CODE_LENGTH);
        for (int i = 0; i < SHORT_CODE_LENGTH; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }

    // Custom exception for expiration
    public static class UrlExpiredException extends RuntimeException {
        public UrlExpiredException(String message) {
            super(message);
        }
    }
}
