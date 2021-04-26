package org.audreydubois.ayd.service;
import org.audreydubois.ayd.entity.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private List<Item> items = new ArrayList<>();
    public void addItem(Item item){
        items.add(item);
    }

    public void deleteItem(Item item) {
        items.remove(item);
    }

    public List<Item> getItems() {
        return items;
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
