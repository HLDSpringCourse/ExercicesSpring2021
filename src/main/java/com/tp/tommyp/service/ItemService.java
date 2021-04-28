package com.tp.tommyp.service;

import com.tp.tommyp.entity.Item;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author tommy
 * @created 26/04/2021 - 16:50
 * @project tommyp
 */
@Service
public class ItemService {

    private final List<Item> items = new ArrayList<>();

    public Item addItem(String name){
        final Item item = new Item(name);
        items.add(item);
        return item;
    }

    public long deleteItem(String id) {
        final long found = items.stream().filter(s -> s.getId().equals(id)).count();
        items.removeIf(s -> s.getId().equals(id));
        return found;
    }

    public List<Item> getItems() {
        return items;
    }

    public Item updateItem(Item item) throws NotFoundException {
        final Optional<Item> found = items.stream().filter(s -> s.getId().equals(item.equals()));
        if(found.isEmpty()) {
            throw new NotFoundException("Pas trouvé !");
        } else {
            final Item foundItem = found.get();
            items.remove(foundItem);
            foundItem.setName(item.getName());
            items.add(foundItem);
            return foundItem;
        }
    }

    public Item get(Long id) {
        for (Item item : items){
            if(item.getId() == id){
                return item;
            }
        }
        return null;
    }
}
