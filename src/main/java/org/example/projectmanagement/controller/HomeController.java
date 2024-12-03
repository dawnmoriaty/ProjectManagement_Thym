package org.example.projectmanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // Trang chủ
    @GetMapping("/home")
    public String home() {
        return "home";
    }
}
