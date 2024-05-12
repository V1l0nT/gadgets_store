package me.vilkov.gadgets_store.controller;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import me.vilkov.gadgets_store.service.AuthService;
import me.vilkov.gadgets_store.service.GadgetService;
import me.vilkov.gadgets_store.service.UserService;

@Controller
public class GadgetController {
    @Getter
    private GadgetService gadgetService;
    private  UserService userService;
    private  AuthService authService;

    @Autowired
    public GadgetController setGadgetService(GadgetService gadgetService) {
        this.gadgetService = gadgetService;
        return this;
    }

    @Autowired
    public GadgetController setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    @Autowired
    public GadgetController setAuthService(AuthService authService) {
        this.authService = authService;
        return this;
    }

    @Transactional
    @GetMapping("/cart")
    public String cart(Model model) {
        model.addAttribute("gadgets", userService.getCart());
        model.addAttribute("totalPrice", userService.getCartTotalPrice());
        model.addAttribute("authUser", authService.getAuthUserOrNull());

        return "cart";
    }

    @GetMapping("/order/{id}")
    public String order(Model model, @PathVariable("id") int id) {
        model.addAttribute("order", userService.getOrderById(id));
        model.addAttribute("authUser", authService.getAuthUserOrNull());
        return "order";
    }

    @PostMapping("/cart/add-gadget/{id}")
    public String addGadgetToCart(@PathVariable("id") int id) {
        if (userService.addToCartByGadgetId(id)) {
            return "redirect:/cart";
        } else {
            return "redirect:?error";
        }
    }

    @PostMapping("/cart/remove-gadget/{id}")
    public String removeGadgetFromCart(@PathVariable("id") int id) {
        userService.removeFromCartByGadgetId(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/create-order")
    public String orderCreate() {
        var order = userService.order();
        return "redirect:/order/" + order.getId();
    }

}
