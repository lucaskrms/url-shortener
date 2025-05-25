package com.lucaskrms.urlshortener.controller;

import com.lucaskrms.urlshortener.dto.UrlRequest;
import com.lucaskrms.urlshortener.dto.UrlResponse;
import com.lucaskrms.urlshortener.service.UrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UrlController {

    private final UrlService urlService;

    public UrlController(UrlService urlService) {
        this.urlService = urlService;
    }

    @PostMapping("/shorten-url")
    public ResponseEntity<UrlResponse> shortenUrl(@RequestBody UrlRequest request,
                                                  HttpServletRequest servletRequest) {
        UrlResponse response = urlService.shortenUrl(request, servletRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> redirect(@PathVariable("id") String id) {
        return urlService.redirect(id);
    }

}
