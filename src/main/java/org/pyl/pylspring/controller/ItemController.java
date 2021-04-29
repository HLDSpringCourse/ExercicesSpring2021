package org.pyl.pylspring.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.exception.APIException;
import org.pyl.pylspring.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import util.ErrorMessage;

import java.util.List;

@RestController
@RequestMapping("/items")
@Api(value = "items API", produces = "", consumes = "", tags = "Items", protocols = "GET, POST, PUT, DELETE")
@AllArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @ApiOperation(value = "Voir la liste de toutes les items", response = ItemDTO[].class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste bien receptionnée"),
    })
    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public List<ItemDTO> getAll() {

        return itemService.getAll();
    }

    @ApiOperation(value = "Voir une item", response = ItemDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item bien receptionnée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "Item non trouvée"),
    })
    @ApiParam(name = "{id}", required = true)
    @GetMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public ItemDTO get(@PathVariable("id") String id) throws APIException {

        return itemService.get(id);
    }

    @ApiOperation(value = "Creer une item", response = ItemDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item bien créée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "Code region inexistant"),
    })
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemDTO create(@RequestBody ItemDTO itemDTO) throws APIException {

        return itemService.create(itemDTO);
    }

    @ApiOperation(value = "Modifier une item", response = ItemDTO.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item bien modifiée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "Item non trouvée ou Code region inexistant"),
    })
    @PutMapping
    @ResponseStatus(code = HttpStatus.OK)
    public ItemDTO update(@RequestBody ItemDTO itemDTO) throws APIException {

        return itemService.update(itemDTO);
    }

    @ApiOperation(value = "Supprimer une item", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item bien supprimée"),
            @ApiResponse(responseCode = "400", description = "Requete erronée"),
            @ApiResponse(responseCode = "404", description = "Item non trouvée"),
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public long delete(@PathVariable("id") String id) throws APIException {
        return itemService.delete(id);
    }

    // todo: deplaçable dans un fichier ?
    @ExceptionHandler({APIException.class})
    public ResponseEntity<ErrorMessage> handleAPIException(APIException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage()), e.getHttpStatus());
    }


}
