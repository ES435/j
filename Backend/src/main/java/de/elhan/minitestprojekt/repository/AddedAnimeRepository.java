package de.elhan.minitestprojekt.repository;

import de.elhan.minitestprojekt.model.AddedAnime;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddedAnimeRepository extends MongoRepository<AddedAnime, String> {
    boolean existsByMalId(Integer malId);
}