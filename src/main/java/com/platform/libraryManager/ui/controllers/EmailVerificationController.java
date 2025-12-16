package com.platform.libraryManager.ui.controllers;


import com.platform.libraryManager.utils.helpers.RouteAttributeHelper;
import com.platform.libraryManager.dto.responses.endpoints.emailVerification.verify.VerifyEmailResponse;
import com.platform.libraryManager.logic.services.EmailVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/email-verification")
public class EmailVerificationController {

    @Autowired private EmailVerificationService emailVerificationService;

    @GetMapping("/{token}")
    public String verifyEmail(
            @PathVariable("token") String token,
            Model model
    ) {
        final VerifyEmailResponse verifyEmailResponse = this.emailVerificationService.verifyEmail(token);
        RouteAttributeHelper.addModelAttributes(model, Map.of(verifyEmailResponse.success() ? "successMessage" : "errorMessage", verifyEmailResponse.getMessage()));

        return "email-verification";
    }
}
