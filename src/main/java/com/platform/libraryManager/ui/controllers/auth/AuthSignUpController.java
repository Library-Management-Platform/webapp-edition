package com.platform.libraryManager.ui.controllers.auth;


import com.platform.libraryManager.utils.helpers.RedirectHelper;
import com.platform.libraryManager.dto.payloads.auth.SignUpAuthPayload;
import com.platform.libraryManager.dto.responses.endpoints.auth.signUp.AuthSignUpResponse;
import com.platform.libraryManager.logic.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping("/sign-up")
public class AuthSignUpController {

    @Autowired private AuthService authService;

    @GetMapping() public String signUp() { return "sign-up"; }

    @PostMapping()
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
