package de.elhan.minitestprojekt.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class HttpClientConfig {
    @Bean WebClient webClient() {
        return WebClient.builder().baseUrl("https://api.jikan.moe/v4").build();
    }
}