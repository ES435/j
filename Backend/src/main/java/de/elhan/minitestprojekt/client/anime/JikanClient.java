package de.elhan.minitestprojekt.client.anime;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class JikanClient {

    private final WebClient webClient;

    // Spring injiziert automatisch den WebClient aus HttpClientConfig
    public JikanClient(WebClient webClient) {
        this.webClient = webClient;
    }

    // Fragt die Jikan-API ab: /anime?q={query}
    public String searchAnime(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/anime")
                        .queryParam("q", query)
                        .build())
                .retrieve() // Anfrage ausführen
                .bodyToMono(String.class) // Antwort als String (JSON)
                .block(); // blockiert bis Antwort da ist (für einfache Nutzung)
    }
}