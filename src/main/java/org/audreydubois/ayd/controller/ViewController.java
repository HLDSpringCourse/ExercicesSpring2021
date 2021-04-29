package org.audreydubois.ayd.controller;

import org.audreydubois.ayd.dto.ItemDTO;
import org.audreydubois.ayd.entity.Item;
import org.audreydubois.ayd.exception.ItemNotFoundException;
import org.audreydubois.ayd.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class ViewController {
    private final ItemService itemService;

    public ViewController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping(path = "/index")
    public String index( Model model){
        model.addAttribute("items", itemService.getAll().getBody());
        model.addAttribute("item", new ItemDTO());
        return "index";
    }
    @GetMapping(path = "/index/{id}")
    public String viewItem( Model model, @PathVariable Long id){
        model.addAttribute("item", itemService.get(id).getBody());
        return "view";
    }
    @RequestMapping(path = "/delete/{id}")
    public String delete(@PathVariable Long id){
        itemService.delete(id);
        return "redirect:/index";
    }

    @PatchMapping(path = "/patch/{id}")
    public String patch(@ModelAttribute Item item, @PathVariable Long id){
        itemService.patchItem(item, id);
        return "redirect:/index/{id}";
    }

    @PostMapping(path="/add")
    public String create(@ModelAttribute ItemDTO item){
        itemService.add(item);
        return "redirect:/index";

    }

}
