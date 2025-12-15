package com.platform.libraryManager.controllers.auth;


import com.platform.libraryManager.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class AuthLogoutController {

    @Autowired private AuthService authService;


    @GetMapping()
    public String logout() {
        return "redirect:/login";
    }

}
