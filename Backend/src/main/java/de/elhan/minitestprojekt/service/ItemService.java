package de.elhan.minitestprojekt.service;

import de.elhan.minitestprojekt.model.Item;

import java.util.List;

public interface ItemService {
    Item createItem(String text);
    List<Item> getAllItems();
}
