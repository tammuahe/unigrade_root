package com.tlu.unigrade.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.genai.Client;

@Configuration
public class GenaiConfig {

    @Value("${google.apikey}")
    private String apikey;

    @Bean
    public Client client() {
        return new Client.Builder().apiKey(apikey).build();
    }
}
