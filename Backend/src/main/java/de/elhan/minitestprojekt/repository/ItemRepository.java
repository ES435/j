package de.elhan.minitestprojekt.repository;

import de.elhan.minitestprojekt.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {
    // optional: eigene Suchmethoden, z. B.
    // List<Item> findByText(String text);
}
