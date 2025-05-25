package com.lucaskrms.urlshortener.service;

import com.lucaskrms.urlshortener.dto.UrlRequest;
import com.lucaskrms.urlshortener.dto.UrlResponse;
import com.lucaskrms.urlshortener.entities.UrlEntity;
import com.lucaskrms.urlshortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDateTime;

@Service
public class UrlService {

    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    public UrlResponse shortenUrl(UrlRequest request, HttpServletRequest servletRequest) {
        String id;
        do {
            id = RandomStringUtils.randomAlphanumeric(5, 10);
        } while (urlRepository.existsById(id));

        urlRepository.save(new UrlEntity(id, request.url(), LocalDateTime.now().plusMinutes(1)));

        String redirectUrl = servletRequest.getRequestURL()
                .toString()
                .replace("shorten-url", id);

        return new UrlResponse(redirectUrl);
    }

    public ResponseEntity<Void> redirect(String id) {
        var url = urlRepository.findById(id);

        if (url.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url.get().getOriginalUrl()));

        return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
    }

}
