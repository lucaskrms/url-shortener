package com.lucaskrms.urlshortener.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String Id;

    private String originalUrl;

    @Indexed(expireAfter = "0s")
    private LocalDateTime expiresAt;
}
