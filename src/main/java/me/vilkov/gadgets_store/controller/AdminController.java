package me.vilkov.gadgets_store.controller;

import me.vilkov.gadgets_store.domain.model.Gadget;
import me.vilkov.gadgets_store.service.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import me.vilkov.gadgets_store.service.OrderService;
import me.vilkov.gadgets_store.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderService orderService;

    private GadgetService gadgetService;

    private UserService userService;

    @Autowired
    public AdminController setOrderService(OrderService orderService) {
        this.orderService = orderService;
        return this;
    }

    @Autowired
    public AdminController setGadgetService(GadgetService gadgetService) {
        this.gadgetService = gadgetService;
        return this;
    }

    @Autowired
    public AdminController setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    @GetMapping("/gadgets")
    public String gadgets(Model model) {
        model.addAttribute("gadgets", gadgetService.getAll());
        return "admin_gadgets";
    }

    @GetMapping("/users")
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "admin_users";
    }

    @GetMapping("/orders")
    public String orders(Model model) {
        model.addAttribute("orders", orderService.getAll());
        return "admin_orders";
    }

    @GetMapping("/orders/{id}")
    public String order(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", orderService.getById(id));
        return "admin_order";
    }

    @PostMapping("/orders/{id}/delete")
    public String deleteOrder(@PathVariable("id") int id) {
        orderService.deleteById(id);
        return "redirect:/admin/orders";
    }
    
    @GetMapping("/gadgets/{id}/edit")
    public String editGadget(Model model, @PathVariable("id") int id) {
        model.addAttribute("gadget", gadgetService.getById(id));
        return "admin_edit_gadget";
    }

    @PostMapping("/gadgets/{id}/delete")
    public String deleteGadget(@PathVariable("id") int id) {
        gadgetService.deleteById(id);
        return "redirect:/admin/gadgets";
    }

    @PostMapping("/gadgets/edit")
    public String editGadgetPost(Gadget gadget) {
        gadgetService.save(gadget);
        return "redirect:/admin/gadgets";
    }

    @GetMapping("/gadgets/add")
    public String addGadget(Model model) {
        model.addAttribute("gadget", new Gadget());
        return "admin_create_gadget";
    }

    @PostMapping("/gadgets/add")
    public String addGadgetPost(Gadget gadget) {
        gadgetService.save(gadget);
        return "redirect:/admin/gadgets";
    }
}
