package org.aubm.aubertma.controller;
import org.aubm.aubertma.entity.Customer;
import org.aubm.aubertma.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class ViewController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/view-by-id/{id}")
    public String viewById(@PathVariable String id, Model model){
        model.addAttribute("customer",customerService.getCustomerById(Integer.parseInt(id)));
        return "view-by-id";
    }

    @GetMapping("/view")
    public String view(Model model){
        model.addAttribute("customers",customerService.getCustomers());
        return "view";
    }

    @GetMapping("/showAddCustomer")
    public String showAddCustomer(Model model){
        Customer newCustomer = new Customer(0,"","");
        model.addAttribute("Customer",newCustomer);

        return "addCustomer";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(Model model, @ModelAttribute("Customer") Customer newCustomer){
        customerService.addCustomer(new Customer(newCustomer.getId(), newCustomer.getName(), newCustomer.getCodeINSEE()));
        return "redirect:/view";
    }
}
