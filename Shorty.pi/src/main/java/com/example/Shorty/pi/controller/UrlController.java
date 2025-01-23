package com.example.Shorty.pi.controller;

import com.example.Shorty.pi.Entity.Url;
import com.example.Shorty.pi.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/shorty.pi")
public class UrlController {

    @Autowired
    private UrlService service;








    //call it use http://shorty.pi/get?shortUrl=Vw1K4I

    @GetMapping("/get")
    public ResponseEntity<Void> getByUrl(@RequestParam String shortUrl) {
        Optional<Url> urlOptional = service.findByShortUrl(shortUrl);
        if (urlOptional.isPresent()) {
            Url url = urlOptional.get();
            return ResponseEntity.status(302)  // HTTP 302 for temporary redirect
                    .location(URI.create(url.getOriginalUrl()))  // Redirect to original URL
                    .build();
        }
        return ResponseEntity.notFound().build();  // Return 404 if not found
    }


    @PostMapping("/v1")
    public ResponseEntity<Url> create(@RequestParam String original) {
        Url shortenedUrl = service.shortenUrl(original);  // Shorten the original URL
        return ResponseEntity.ok(shortenedUrl);  // Return the shortened URL
    }
}
