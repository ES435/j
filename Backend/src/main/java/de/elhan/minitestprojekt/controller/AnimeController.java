package de.elhan.minitestprojekt.controller;

import de.elhan.minitestprojekt.service.anime.AnimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnimeController {

    private final AnimeService animeService;

    public AnimeController(AnimeService animeService) {
        this.animeService = animeService;
    }

    // Beispiel: GET /api/anime?q=naruto
    @GetMapping("/api/anime")
    public String search(@RequestParam String q) {
        return animeService.searchAnime(q); // rohes JSON (String) zurück
    }
}