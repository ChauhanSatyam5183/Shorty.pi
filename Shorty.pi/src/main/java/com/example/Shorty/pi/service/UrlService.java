package com.example.Shorty.pi.service;

import com.example.Shorty.pi.Entity.Url;
import com.example.Shorty.pi.Repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UrlService {
    @Autowired
    private UrlRepository repo;

      // Base URL for the short URL

    // Method to generate a random short URL
    public String generateShortUrl(String originalUrl) {
        StringBuilder shortUrl = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rand = new Random();

        for (int i = 0; i < 6; i++) {
            shortUrl.append(chars.charAt(rand.nextInt(chars.length())));
        }

        return  shortUrl.toString();  // Return full short URL
    }

    // Method to shorten the original URL and save it to the database
    public Url shortenUrl(String originalUrl) {
        String shortUrl = generateShortUrl(originalUrl);


        return repo.save(Url.builder()
                .originalUrl(originalUrl)
                .shortUrl(shortUrl)
                .build());
    }

    // Method to find a URL by its short URL
    public Optional<Url> findByShortUrl(String shortUrl) {
        return repo.findByShortUrl(shortUrl);  // Correct repository method
    }
}
