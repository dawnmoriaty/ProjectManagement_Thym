package org.example.projectmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.example.projectmanagement.model.entity.Cart;
import org.example.projectmanagement.model.entity.CartItems;
import org.example.projectmanagement.service.ICartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final ICartService cartService;
    @GetMapping("/home")
    public String customerHome() {
        return "redirect:customer-home";
    }
}

