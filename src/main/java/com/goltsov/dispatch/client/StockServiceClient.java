package com.goltsov.dispatch.client;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class StockServiceClient {

    private final RestTemplate restTemplate;

    private final String stockServiceEndpoint;

    public StockServiceClient(@Autowired RestTemplate restTemplate,
                              @Value("${dispatch.stockServiceEndpoint}") String stockServiceEndpoint) {
        this.restTemplate = restTemplate;
        this.stockServiceEndpoint = stockServiceEndpoint;
    }

    public String checkAvailability(String item) {

        try {
            ResponseEntity<String> response = restTemplate
                    .getForEntity(stockServiceEndpoint + "?item=" + item, String.class);
            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("error " + response.getStatusCode());
            }
            return response.getBody();
        } catch (HttpServerErrorException | ResourceAccessException exception) {
            log.warn("Failure calling external service", exception);
            throw exception;
        }
    }
}
