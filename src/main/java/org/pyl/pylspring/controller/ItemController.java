package org.pyl.pylspring.controller;

import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ItemDTO> getAll() {

        return new ArrayList<ItemDTO>();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO get(@PathVariable("id") String id) {

        return new ItemDTO();
    }

    @PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO create(@RequestBody ItemDTO itemDTO) {

        return new ItemDTO();
    }

    @PutMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ItemDTO update(@RequestBody ItemDTO itemDTO) {

        return new ItemDTO();
    }

    @DeleteMapping(path = "/{id}")
    public long deleteItem(@PathVariable("id") String id) {

        return 123L;
    }


}
