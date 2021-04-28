package com.tp.tommyp.controller;

import com.tp.tommyp.entity.Item;
import com.tp.tommyp.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author tommy
 * @created 26/04/2021 - 16:50
 * @project tommyp
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    // @Autowired
    // ItemService itemService


    @Autowired
    private ItemService service;

    @PostMapping(path = "/post/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Item addItem(@PathVariable("name") String name)
    {
        return service.addItem(name);
    }

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getItem(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body(new Item("First item"));
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteItem(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.OK).body("{'success':true}");
    }

    @PutMapping(path = "/put/{id}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> putItem(@PathVariable int id, @PathVariable String name) {
        return ResponseEntity.status(HttpStatus.OK).body("{'success':true}");
    }
}