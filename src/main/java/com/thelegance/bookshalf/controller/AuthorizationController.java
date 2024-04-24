package com.thelegance.bookshalf.controller;

import com.thelegance.bookshalf.model.User;
import com.thelegance.bookshalf.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorizationController
{
    private final UserServiceImpl userService;

    @Autowired
    public AuthorizationController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model)
    {
        try
        {
            userService.addUser(user);
            return "redirect:/login";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", "User already exists");
            return "registration";
        }
    }
}
