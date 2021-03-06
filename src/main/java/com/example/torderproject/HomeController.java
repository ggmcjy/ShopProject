package com.example.torderproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/login")
    public String login() {

        return "accounts/login";
    }

    @GetMapping("/cart-list")
    public String cart() {
        return "cart/cart-list";
    }

    @GetMapping("/payment-page")
    public String payment() {
        return "payment/payment-page";
    }
}
