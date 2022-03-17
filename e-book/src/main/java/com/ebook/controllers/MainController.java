package com.ebook.controllers;

import com.ebook.entities.User;
import com.ebook.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class MainController {
    @GetMapping("/")
    public String homePage() {
        return "home";
    }

    @GetMapping("/authenticated")
    public String usecuredPage() {
        return "Люди которые зарегались";
    }

    @GetMapping("/read_profile")
    public String pageForRead() {
        return "тут можно читать профили";
    }

    @GetMapping("/admin")
    public String pageForAdmin() {
        return "страница для админов";
    }

    @GetMapping("/vendor")
    public String pageForVendor() {
        return "Страница для продавцов";
    }

}
