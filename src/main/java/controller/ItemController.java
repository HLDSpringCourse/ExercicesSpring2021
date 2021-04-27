package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.lfc.entity.Item;
import org.lfc.service.ItemService;

@RestController
@RequestMapping("/item")
public class ItemController {
	@Autowired
	ItemService itemService;
    
	@PostMapping(path = "/post/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Item> postItem(@PathVariable String name)
	{
		return ResponseEntity.status(HttpStatus.OK).body(itemService.add(name));
	}

	@GetMapping
	public ResponseEntity<ArrayList<Item>> getAllItems()
	{
		return ResponseEntity.status(HttpStatus.OK).body(itemService.getAll());
	}

    @GetMapping(path = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> getItem(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.get(Long.valueOf(id)));
    }

	@DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> deleteItem(@PathVariable int id) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.delete(Long.valueOf(id)));
    }

	@PutMapping(path = "/put/{id}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Item> putItem(@PathVariable int id, @PathVariable String name) {
		return ResponseEntity.status(HttpStatus.OK).body(itemService.put(Long.valueOf(id), name));
    }
}