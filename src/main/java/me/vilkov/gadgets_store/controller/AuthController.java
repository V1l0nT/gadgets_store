package me.vilkov.gadgets_store.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import me.vilkov.gadgets_store.domain.dto.UserRegisterDTO;
import me.vilkov.gadgets_store.service.UserService;

@Controller
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController setUserService(UserService userService) {
        this.userService = userService;
        return this;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("user") UserRegisterDTO userRegisterDTO) {
        return "register";
    }

    @PostMapping("/register")
    public String registerPost(
            @Valid @ModelAttribute("user") UserRegisterDTO userRegisterDTO,
            BindingResult result
    ) {
        var existing = userService.findByUsername(userRegisterDTO.getUsername());
        if (existing.isPresent()) {
            result.rejectValue("username", null, "Пользователь с таким именем уже существует");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.register(userRegisterDTO);

        return "redirect:/login";
    }

}
