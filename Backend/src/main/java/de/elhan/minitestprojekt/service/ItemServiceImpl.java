package de.elhan.minitestprojekt.service;

import de.elhan.minitestprojekt.model.Item;
import de.elhan.minitestprojekt.repository.ItemRepository;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(String text) {
        Item item = new Item();
        item.setText(text);
        item.setCreatedAt(Instant.now());
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
