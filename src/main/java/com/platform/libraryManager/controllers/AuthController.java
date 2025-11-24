package com.platform.libraryManager.controllers;

import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.payloads.authPayloads.SignUpAuthPayload;
import com.platform.libraryManager.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired AuthService authService;

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html
    }



    @GetMapping("/sign-up")
    public String signUp() {
        return "sign-up"; // login.html
    }

    @PostMapping("/sign-up")
    public String signUp(SignUpAuthPayload signUpAuthPayload) {
        authService.signUp(signUpAuthPayload);
        return "redirect:/login";
    }




}
