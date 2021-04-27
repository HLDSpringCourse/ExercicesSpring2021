package org.pyl.pylspring.controller;

import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.exception.APIException;
import org.pyl.pylspring.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<ItemDTO> getAll() {

        return itemService.getAll();
    }

    @GetMapping( "/{id}")
    public ItemDTO get(@PathVariable("id") String id) throws APIException {

        return itemService.get(id);
    }

    @PostMapping
    public ItemDTO create(@RequestBody ItemDTO itemDTO) throws APIException {

        return itemService.create(itemDTO);
    }

    @PutMapping
    public ItemDTO update(@RequestBody ItemDTO itemDTO) throws APIException {

        return itemService.update(itemDTO);
    }

    @DeleteMapping( "/{id}")
    public long delete(@PathVariable("id") String id) throws APIException {
        return itemService.delete(id);
    }


    @ExceptionHandler({APIException.class})
    public ResponseEntity<ErrorMessage> handleAPIException(APIException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getHttpStatus());
    }

    public class ErrorMessage {
        public String errorMessage;

        public ErrorMessage(String errorMessage) {
            this.errorMessage = errorMessage;
        }
    }

}
