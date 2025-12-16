package com.platform.libraryManager.ui.controllers.auth;

import com.platform.libraryManager.shared.enums.UserTypeEnum;
import com.platform.libraryManager.shared.helpers.RedirectHelper;
import com.platform.libraryManager.dto.payloads.auth.LoginAuthPayload;
import com.platform.libraryManager.dto.responses.endpoints.auth.login.AuthLoginResponse;
import com.platform.libraryManager.dto.responses.endpoints.auth.login.AuthLoginSuccessResponse;
import com.platform.libraryManager.logic.services.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@RequestMapping("/login")
public class AuthLoginController {

    @Autowired private AuthService authService;

    @GetMapping() public String login() { return "login"; }

    @PostMapping()
    public String login(
            LoginAuthPayload loginAuthPayload,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request
    ) {

        final AuthLoginResponse authLoginResponse = authService.login(loginAuthPayload, request);

        final Map<UserTypeEnum, String> redirectMap = Map.of(
                UserTypeEnum.ADMIN, "redirect:/admin/manage-admins",
                UserTypeEnum.CLIENT, "redirect:/client/resources",
                UserTypeEnum.LIBRARIAN, "redirect:/librarian/dashboard"
        );

        return RedirectHelper.addFlashAttributesAndRedirect(
                redirectAttributes,
                Map.of(
                        "message", authLoginResponse.getMessage(),
                        "username", authLoginResponse.success()? ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUsername() : "",
                        "role", authLoginResponse.success()? ((AuthLoginSuccessResponse) authLoginResponse).getUser().getUserType().name() : ""
                ),
                authLoginResponse.success()? redirectMap.get(((AuthLoginSuccessResponse) authLoginResponse).getUser().getUserType()) : "redirect:/login"
        );
    }
}
