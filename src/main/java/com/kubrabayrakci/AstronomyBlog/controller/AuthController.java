package com.kubrabayrakci.AstronomyBlog.controller;

import com.kubrabayrakci.AstronomyBlog.model.User;
import com.kubrabayrakci.AstronomyBlog.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    @GetMapping("/register")
    public String getRegisterPage(Model model){

        User user = new User();
        model.addAttribute("newUser", user);

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(Model model, @ModelAttribute("newUser") User newUser){

        userDetailsService.saveUser(newUser);

        return "redirect:/login";
    }

}
