package me.vilkov.gadgets_store.controller;

import me.vilkov.gadgets_store.service.GadgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import me.vilkov.gadgets_store.service.AuthService;

@Controller
public class HomeController {
    private GadgetService gadgetService;
    private AuthService authService;

    @Autowired
    public HomeController setGadgetService(GadgetService gadgetService) {
        this.gadgetService = gadgetService;
        return this;
    }

    @Autowired
    public HomeController setAuthService(AuthService authService) {
        this.authService = authService;
        return this;
    }

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("gadgets", gadgetService.getAllGadgets());
        model.addAttribute("authUser", authService.getAuthUserOrNull());

        return "index";
    }
}
