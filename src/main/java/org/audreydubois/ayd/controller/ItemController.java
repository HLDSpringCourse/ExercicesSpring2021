package org.audreydubois.ayd.controller;

import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.exception.ItemNotFoundException;
import org.audreydubois.ayd.repository.ItemRepository;
import org.audreydubois.ayd.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    public ItemController(ItemService itemService, ItemRepository repository){
        this.itemService = itemService;
        this.itemRepository = repository;
    }

    @GetMapping(path = "/list")
    public ResponseEntity<List<Item>> getAll() {
        List<Item> items = itemRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(items);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> get(@PathVariable("id") Long id) {
        Item item = itemRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
        return ResponseEntity.status(HttpStatus.OK).body(item);

    }

    @PostMapping(path = "/add")
    public ResponseEntity<Item> add(@RequestBody Item item) {
        if(item != null){
            Item itemSave = itemRepository.save(item);
            return ResponseEntity.status(HttpStatus.OK).body(itemSave);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {

        itemRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Item n°"+id+" deleted");
    }

    @PatchMapping("/patch/{id}")
    public ResponseEntity<Item> replaceEmployee(@RequestBody Item newItem, @PathVariable Long id){
        Item newItemSave =  itemRepository.findById(id).map(
                item -> {
                    item.setName(newItem.getName());
                    return itemRepository.save(item);
                }
        ).orElseGet(()->{
            newItem.setId(id);
            return itemRepository.save(newItem);
        });
        if(newItemSave != null){
            return ResponseEntity.status(HttpStatus.OK).body(newItemSave);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }
}
