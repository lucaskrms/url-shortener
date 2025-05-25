package com.lucaskrms.urlshortener.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "urls")
public class UrlEntity {

    @Id
    private String id;

    private String originalUrl;

    @Indexed(expireAfter = "0s")
    private LocalDateTime expiresAt;
}
