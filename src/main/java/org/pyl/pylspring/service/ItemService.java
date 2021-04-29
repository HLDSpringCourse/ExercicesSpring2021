package org.pyl.pylspring.service;

import org.apache.commons.lang3.StringUtils;
import org.pyl.pylspring.Client.GeoApiClient;
import org.pyl.pylspring.dao.ItemDAO;
import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.entity.Item;
import org.pyl.pylspring.exception.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import util.Constants;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private final GeoApiClient geoApiClient;

    private final ItemDAO itemDAO;

    public ItemService(GeoApiClient geoApiClient, ItemDAO itemDAO) {
        super();

        this.geoApiClient = geoApiClient;
        this.itemDAO = itemDAO;
    }

    public List<ItemDTO> getAll() {
        return itemDAO.findAll().stream().map(this::entityToDto).collect(Collectors.toList());
    }

    public ItemDTO get(String id) throws APIException {
        return entityToDto(getItem(id));
    }

    public ItemDTO create(ItemDTO itemDTO) throws APIException {
        if(!isItemDTOValid(itemDTO)) throw new APIException(Constants.MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);

        itemDTO.setRegionCode(StringUtils.leftPad(itemDTO.getRegionCode(), 2, "0"));

        itemDTO.setRegionName(geoApiClient.getRegion(itemDTO.getRegionCode()));

        final Item item = dtoToEntityNoId(itemDTO);

        itemDAO.save(item);

        return entityToDto(item);
    }

    public ItemDTO update(ItemDTO itemDTO) throws APIException {

        if(!isItemDTOValid(itemDTO)) throw new APIException(Constants.MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);

        itemDTO.setRegionName(geoApiClient.getRegion(itemDTO.getRegionCode()));

        final long itemId = itemDTO.getId();

        itemDAO.findById(itemId).orElseThrow(
                    () -> new APIException(Constants.MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND)
                );

        Item item = itemDAO.save(dtoToEntity(itemDTO));

        return entityToDto(item);
    }


    public Long delete(String id) throws APIException {

        final Item item = getItem(id);

        itemDAO.delete(item);

        return item.getId();
    }

    private ItemDTO entityToDto(Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getRegionCode(), item.getRegionName());
    }

    private Item dtoToEntity(ItemDTO itemDTO) {
        return new Item(itemDTO.getId(), itemDTO.getName(), itemDTO.getRegionCode(), itemDTO.getRegionName());
    }

    private Item dtoToEntityNoId(ItemDTO itemDTO) {
        return new Item(itemDTO.getName(), itemDTO.getRegionCode(), itemDTO.getRegionName());
    }

    private boolean isItemDTOValid(ItemDTO itemDTO) {
        return itemDTO != null
                && itemDTO.getName() != null
                && !itemDTO.getName().isEmpty()
                && itemDTO.getRegionCode() != null
                && !itemDTO.getRegionCode().isEmpty();
    }

    private Item getItem(String id) throws APIException {
        long itemId;

        try {
            itemId = Long.parseLong(id);

        } catch(NumberFormatException e) {
            throw new APIException(Constants.MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);
        }

        return itemDAO.findById(itemId).orElseThrow(() -> new APIException(Constants.MESSAGE_NOT_FOUND, HttpStatus.NOT_FOUND));
    }
}
