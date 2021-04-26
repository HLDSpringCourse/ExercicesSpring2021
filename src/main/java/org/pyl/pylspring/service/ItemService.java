package org.pyl.pylspring.service;

import org.pyl.pylspring.dao.ItemDAO;
import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.exception.APIException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemService {

    private static long currentId = 3L;

    private static final String MESSAGE_NOT_FOUND = "Item non trouvée";
    private static final String MESSAGE_BAD_ITEM = "Item non conforme";

    private final List<ItemDAO> itemDAOList = new ArrayList<>();

    public ItemService() {
        super();
        itemDAOList.add(new ItemDAO(1L, "toto"));
        itemDAOList.add(new ItemDAO(2L, "titi"));
        itemDAOList.add(new ItemDAO(3L, "tutu"));
    }

    public List<ItemDTO> getAll() {
        return itemDAOList.stream().map(this::daoToDto).collect(Collectors.toList());
    }

    public ItemDTO get(String id) throws APIException {

        final long itemId = Long.parseLong(id);

        Optional<ItemDAO> itemDAOOptional = itemDAOList.stream().filter(itemDAO -> itemDAO.getId() == itemId).findFirst();

        if(itemDAOOptional.isEmpty()) throw new APIException(MESSAGE_NOT_FOUND);
        return daoToDto(itemDAOOptional.get());
    }

    public ItemDTO create(ItemDTO itemDTO) {
        // check item here
        ItemDAO itemDAO = dtoToDao(itemDTO);
        currentId++;
        itemDAO.setId(currentId);
        itemDAOList.add(itemDAO);
        return daoToDto(itemDAO);
    }

    public ItemDTO update(ItemDTO itemDTO) throws APIException {
        // dto check here
        final long itemId = itemDTO.getId();


        Optional<ItemDAO> itemDAOOptional = itemDAOList.stream().filter(itemDAO -> itemDAO.getId() == itemId).findFirst();
        if(itemDAOOptional.isEmpty()) throw new APIException(MESSAGE_NOT_FOUND);

        int itemIndex = itemDAOList.indexOf(itemDAOOptional.get());

        itemDAOList.set(itemIndex, dtoToDao(itemDTO));
        return daoToDto(itemDAOList.get(itemIndex));
    }


    public Long delete(String id) throws APIException {
        final long itemId = Long.parseLong(id);
        Optional<ItemDAO> itemDAOOptional = itemDAOList.stream().filter(itemDAO -> itemDAO.getId() == itemId).findFirst();
        if(itemDAOOptional.isEmpty()) throw new APIException(MESSAGE_NOT_FOUND);

        itemDAOList.remove(itemDAOOptional.get());

        return itemId;
    }

    private ItemDTO daoToDto(ItemDAO itemDAO) {
        return new ItemDTO(itemDAO.getId(), itemDAO.getName());
    }

    private ItemDAO dtoToDao(ItemDTO itemDTO) {
        return new ItemDAO(itemDTO.getId(), itemDTO.getName());
    }
}
