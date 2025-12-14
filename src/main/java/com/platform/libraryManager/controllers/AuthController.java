package com.platform.libraryManager.controllers;

import com.platform.libraryManager.enums.UserTypeEnum;
import com.platform.libraryManager.helpers.RedirectHelper;
import com.platform.libraryManager.payloads.auth.LoginAuthPayload;
import com.platform.libraryManager.payloads.auth.SignUpAuthPayload;
import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginErrorResponse;
import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginResponse;
import com.platform.libraryManager.responses.endpoints.auth.login.AuthLoginSuccessResponse;
import com.platform.libraryManager.responses.endpoints.auth.signUp.AuthSignUpResponse;
import com.platform.libraryManager.services.AuthService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
public class AuthController {

    @Autowired private AuthService authService;

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/sign-up")
    public String signUp() { return "sign-up"; }




    @PostMapping("/login")
    public String login(
            LoginAuthPayload loginAuthPayload,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {

        final AuthLoginResponse authLoginResponse = authService.login(loginAuthPayload, request);

        return RedirectHelper.addFlashAttributesAndRedirect(
                redirectAttributes,
                Map.of(
                        "message", authLoginResponse.getMessage(),
                        "username", ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUsername(),
                        "role", ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUserType().name()
                ),
                authLoginResponse.success()
                        ? ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUserType().equals(UserTypeEnum.ADMIN) ? "redirect:/admin/dashboard"
                        : ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUserType().equals(UserTypeEnum.CLIENT) ? "redirect:/client/resources"
                        : ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUserType().equals(UserTypeEnum.LIBRARIAN) ? "redirect:/librarian/dashboard"
                        : "redirect:/login"
                        : "redirect:/login"
        );
    }


    @PostMapping("/sign-up")
    public String signUp(
            SignUpAuthPayload signUpAuthPayload,
            RedirectAttributes redirectAttributes
    ) {
        final AuthSignUpResponse authSignUpResponse = authService.signUp(signUpAuthPayload);

        return RedirectHelper.addFlashAttributesAndRedirect(
                redirectAttributes,
                Map.of("message", authSignUpResponse.getMessage()),
                authSignUpResponse.success() ? "redirect:/login" : "redirect:/sign-up"
        );
    }




}
