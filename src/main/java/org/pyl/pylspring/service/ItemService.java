package org.pyl.pylspring.service;

import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.dto.RegionDTO;
import org.pyl.pylspring.entity.Item;
import org.pyl.pylspring.exception.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static long currentId = 3L;

    private static final String MESSAGE_NOT_FOUND = "Item non trouvée";
    private static final String MESSAGE_BAD_ITEM = "Item non conforme";

    private final List<Item> itemList = new ArrayList<>();

    private final RestTemplate restTemplate = new RestTemplate();


    public ItemService() {
        super();
        itemList.add(new Item(1L, "toto"));
        itemList.add(new Item(2L, "titi"));
        itemList.add(new Item(3L, "tutu"));
    }

    public List<ItemDTO> getAll() {

        return itemList.stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public ItemDTO get(String id) throws APIException {
        return entityToDto(getItem(id));
    }

    public ItemDTO create(ItemDTO itemDTO) throws APIException {
        if(!isItemDTOValid(itemDTO)) throw new APIException(MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);

        final Item item = dtoToEntity(itemDTO);
        currentId++;
        item.setId(currentId);
        itemList.add(item);
        return entityToDto(item);
    }

    public ItemDTO update(ItemDTO itemDTO) throws APIException {

        if(!isItemDTOValid(itemDTO)) throw new APIException(MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);

        final long itemId = itemDTO.getId();

        final Item item = itemList.stream().filter(aItem -> aItem.getId() == itemId).findFirst().orElseThrow(
                () -> new APIException(MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND)
        );

        int itemIndex = itemList.indexOf(item);

        itemList.set(itemIndex, dtoToEntity(itemDTO));
        return entityToDto(itemList.get(itemIndex));
    }


    public Long delete(String id) throws APIException {

        final Item item = getItem(id);

        itemList.remove(item);

        return item.getId();
    }

    public List<RegionDTO> getAllRegions() {
        return Arrays.asList(restTemplate.getForObject(Constants.API_GOV_BASE_URL+Constants.API_GOV_REGIONS_URL, RegionDTO[].class));
    }

    public RegionDTO getRegion(String code) {
        return restTemplate.getForObject(Constants.API_GOV_BASE_URL+Constants.API_GOV_REGIONS_URL+"/"+code, RegionDTO.class);
    }

    private ItemDTO entityToDto(Item item) {
        return new ItemDTO(item.getId(), item.getName());
    }

    private Item dtoToEntity(ItemDTO itemDTO) {
        return new Item(itemDTO.getId(), itemDTO.getName());
    }


    private boolean isItemDTOValid(ItemDTO itemDTO) {
        return itemDTO != null
                && itemDTO.getName() != null
                && !itemDTO.getName().isEmpty();
    }

    private Item getItem(String id) throws APIException {
        long itemId;

        try {
            itemId = Long.parseLong(id);

        } catch(NumberFormatException e) {
            throw new APIException(MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);
        }

        return itemList.stream().filter(aItem -> aItem.getId() == itemId).findFirst().orElseThrow(
                () -> new APIException(MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND)
        );
    }
}
