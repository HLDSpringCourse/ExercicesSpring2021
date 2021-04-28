package org.audreydubois.ayd.controller;

import io.swagger.annotations.Api;
import org.audreydubois.ayd.dto.ItemDTO;
import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.exception.ItemNotFoundException;
import org.audreydubois.ayd.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="items API", produces = "", consumes="" , tags="Items", protocols="GET, POST, PATCH, DELETE")
@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAll() {
        return itemService.getAll();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Item> get(@PathVariable("id") Long id) {
        try {
            return itemService.get(id);
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PostMapping
    public ResponseEntity<Item> add(@RequestBody ItemDTO item) {
        try {
            return itemService.add(item);
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id) {
            return itemService.delete(id);
       }

    @PatchMapping("/{id}")
    public ResponseEntity<Item> replaceItem(@RequestBody ItemDTO newItem, @PathVariable Long id){
        try {
            return itemService.patch(newItem,id);
        }catch (ItemNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
