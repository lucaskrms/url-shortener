package com.lucaskrms.urlshortener.service;

import com.lucaskrms.urlshortener.dto.UrlRequest;
import com.lucaskrms.urlshortener.dto.UrlResponse;
import com.lucaskrms.urlshortener.entities.UrlEntity;
import com.lucaskrms.urlshortener.repository.UrlRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

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
}
