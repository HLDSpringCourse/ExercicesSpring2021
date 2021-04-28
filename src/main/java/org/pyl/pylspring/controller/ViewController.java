package org.pyl.pylspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.pyl.pylspring.dto.ItemDTO;
import org.pyl.pylspring.exception.APIException;
import org.pyl.pylspring.service.ItemService;
import org.pyl.pylspring.dto.ItemDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
public class ViewController {

    private final ItemService itemService;

    public ViewController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/itemsview")
    public String getView(Model model) {
        model.addAttribute("items", itemService.getAll());
        model.addAttribute("itemDTO", new ItemDTO());
        return "view";
    }

    @GetMapping("/itemsview/{id}")
    public String getViewById(@PathVariable("id") String id, Model model) throws APIException {
        model.addAttribute("item", itemService.get(id));
        return "view-by-id";
    }

    @PostMapping("/additem")
    public String create(@ModelAttribute("itemDTO") ItemDTO itemDTO) {
        try {
            itemService.create(itemDTO);
            return "redirect:/itemsview";

        } catch(APIException e) {
            return "additem";
        }
    }

    @DeleteMapping( "/deleteitem/{id}")
    public String delete(@PathVariable("id") String id) throws APIException {
        itemService.delete(id);
        return "redirect:/itemsview";
    }

}
