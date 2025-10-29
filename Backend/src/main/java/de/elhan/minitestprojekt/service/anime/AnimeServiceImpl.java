package de.elhan.minitestprojekt.service.anime;

import de.elhan.minitestprojekt.client.anime.JikanClient;
import org.springframework.stereotype.Service;

@Service
public class AnimeServiceImpl implements AnimeService {

    private final JikanClient jikanClient;

    public AnimeServiceImpl(JikanClient jikanClient) {
        this.jikanClient = jikanClient;
    }

    @Override
    public String searchAnime(String query) {
        if (query == null || query.isBlank()) {
            return "Query cannot be empty";
        }
        // Hier könnte später noch Caching, Fehlerbehandlung, DTO-Mapping etc. hinzukommen
        return jikanClient.searchAnime(query);
    }
}