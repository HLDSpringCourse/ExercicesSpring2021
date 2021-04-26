package org.audreydubois.ayd.controller;

import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> items = itemService.getItems();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> get(@PathVariable("id") Long id) {
        Item item = itemService.get(id);
        return ResponseEntity.status(HttpStatus.OK).body(item);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<Item> add(@RequestBody Item item) {
        if(item != null){
            itemService.addItem(item);
            return ResponseEntity.status(HttpStatus.OK).body(item);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }

    @DeleteMapping(path = "/delete")
    public ResponseEntity<String> deleteItem(@RequestBody Item item) {

        itemService.deleteItem(item);
        return ResponseEntity.status(HttpStatus.OK).body("Item deleted");
    }
}
