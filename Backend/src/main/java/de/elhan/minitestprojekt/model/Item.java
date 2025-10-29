package de.elhan.minitestprojekt.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document("items")
@Data
@NoArgsConstructor
public class Item {

    @Id
    private String id;             // Primärschlüssel (_id in Mongo)
    private String text;           // Inhalt
    private Instant createdAt;     // Zeitstempel

    // Wird automatisch beim Erstellen gesetzt
    public Item(String text) {
        this.text = text;
        this.createdAt = Instant.now();
    }
}