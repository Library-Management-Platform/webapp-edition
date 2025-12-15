package com.platform.libraryManager.controllers.auth;


import com.platform.libraryManager.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class AuthLogoutController {

    @Autowired private AuthService authService;


    @GetMapping()
    public String logout(HttpServletRequest request) {
        authService.logout(request);
        return "redirect:/login";
    }

}
