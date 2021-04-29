package org.pyl.pylspring.controller;

import lombok.extern.slf4j.Slf4j;
import org.pyl.pylspring.Client.GeoApiClient;
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
    private final GeoApiClient geoApiClient;

    public ViewController(ItemService itemService, GeoApiClient geoApiClient) {

        this.itemService = itemService;
        this.geoApiClient = geoApiClient;
    }

    @GetMapping("/itemsview")
    public String getView(Model model) {
        model.addAttribute("items", itemService.getAll());
        model.addAttribute("itemDTO", new ItemDTO());
        model.addAttribute("regionCodeList", geoApiClient.getAllRegions());
        return "view";
    }

    @GetMapping("/itemsview/{id}")
    public String getViewById(@PathVariable("id") String id, Model model) throws APIException {
        model.addAttribute("item", itemService.get(id));
        model.addAttribute("regionCodeList", geoApiClient.getAllRegions());
        return "view-by-id";
    }

    // error managment ? : https://stackoverflow.com/questions/47740583/spring-mvc-how-to-pass-a-parameter-to-thymeleaf-view-when-redirecting
    @PostMapping("/additem")
    public String create(@ModelAttribute("itemDTO") ItemDTO itemDTO)  {
            try {
                itemService.create(itemDTO);
            } catch (APIException e) {
                return "/error";
            }

            return "redirect:/itemsview";
    }

    @PutMapping("/updateitem")
    public String update(@ModelAttribute("itemDTO") ItemDTO itemDTO)  {
        try {
            itemService.update(itemDTO);
        } catch (APIException e) {
            log.error(e.toString());
        }

        return "redirect:/itemsview/"+itemDTO.getId();
    }

    @DeleteMapping( "/deleteitem/{id}")
    public String delete(@PathVariable("id") String id) throws APIException {

        try {
            itemService.delete(id);
        } catch (APIException e) {
            log.error(e.toString());
        }

        return "redirect:/itemsview";
    }

}
