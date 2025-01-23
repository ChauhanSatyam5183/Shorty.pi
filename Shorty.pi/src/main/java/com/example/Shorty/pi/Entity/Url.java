package com.example.Shorty.pi.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "url")
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "short_url", nullable = false, unique = true)
    private String shortUrl;  // shortened URL (camelCase)

    @Column(name = "original_url", nullable = false)
    private String originalUrl;  // original URL (camelCase)
}
