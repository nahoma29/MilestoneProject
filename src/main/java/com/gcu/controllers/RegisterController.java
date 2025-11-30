package com.gcu.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.gcu.models.RegisterModel;
import com.gcu.services.RegisterServiceInterface;

import jakarta.validation.Valid;

@Controller
public class RegisterController {

    private final RegisterServiceInterface registerService;

    public RegisterController(RegisterServiceInterface registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String display(Model model) {
        model.addAttribute("title", "Register Form");
        model.addAttribute("registerModel", new RegisterModel());
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@Valid RegisterModel registerModel,
                             BindingResult bindingResult,
                             Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("title", "Register Form");
            return "register";
        }

        boolean registered = registerService.register(
                registerModel.getUsername(),
                registerModel.getPassword()
        );

        if (!registered) {
            bindingResult.reject("registrationError", "Registration failed. Please try again.");
            model.addAttribute("title", "Register Form");
            model.addAttribute("registrationError", "Registration failed. Please try again.");
            return "register";
        }

        return "redirect:/login";
    }

}
