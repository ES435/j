package de.elhan.minitestprojekt.controller;

import de.elhan.minitestprojekt.model.AddedAnime;
import de.elhan.minitestprojekt.repository.AddedAnimeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/anime/add")
public class AddedAnimeController {

    private final AddedAnimeRepository repo;

    public AddedAnimeController(AddedAnimeRepository repo) {
        this.repo = repo;
    }

    // Anime hinzufügen
    @PostMapping
    public AddedAnime add(@RequestBody AddedAnime anime) {
        if (anime.getMalId() != null && repo.existsByMalId(anime.getMalId())) {
            // Wenn schon vorhanden, existierenden Eintrag zurückgeben
            return repo.findAll().stream()
                    .filter(a -> anime.getMalId().equals(a.getMalId()))
                    .findFirst()
                    .orElse(anime);
        }
        return repo.save(anime);
    }

    // Alle hinzugefügten Animes abrufen
    @GetMapping
    public List<AddedAnime> all() {
        return repo.findAll();
    }
}