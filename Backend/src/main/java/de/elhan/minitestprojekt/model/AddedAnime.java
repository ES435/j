package de.elhan.minitestprojekt.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document("added_anime")
@Data
@NoArgsConstructor
public class AddedAnime {

    @Id
    private String id;

    private Integer malId;       // Jikan/MAL Anime-ID
    private String title;        // Anime-Titel
    private String imageUrl;     // Poster-URL
    private Instant createdAt = Instant.now();  // Zeitstempel
}