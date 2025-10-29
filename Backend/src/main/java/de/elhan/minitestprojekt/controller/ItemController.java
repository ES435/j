package de.elhan.minitestprojekt.controller;

import de.elhan.minitestprojekt.model.Item;
import de.elhan.minitestprojekt.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Neuen Eintrag speichern
    @PostMapping
    public Item addItem(@RequestBody Item item) {
        return itemService.createItem(item.getText());
    }

    // Alle Einträge abrufen
    @GetMapping
    public List<Item> getAllItems() {
        return itemService.getAllItems();
    }
}