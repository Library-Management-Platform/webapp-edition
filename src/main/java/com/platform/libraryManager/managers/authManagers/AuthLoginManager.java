package com.platform.libraryManager.managers.authManagers;

import com.platform.libraryManager.helpers.JSONHelper;
import com.platform.libraryManager.payloads.authPayloads.LoginAuthPayload;
import com.platform.libraryManager.providers.JWTProvider;
import com.platform.libraryManager.providers.PasswordHashingProvider;
import com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses.AuthLoginResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses.AuthLoginSuccessResponse;
import com.platform.libraryManager.responses.endpointResponses.authResponses.loginResponses.AuthLoginWrongPasswordErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses.GetUniqueClientResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthLoginManager {

    @Autowired PasswordHashingProvider passwordHashingProvider;
    @Autowired JWTProvider jwtProvider;

    private boolean verifyPassword(String inputPassword, String userHashedPassword) {
        return passwordHashingProvider.verify(inputPassword, userHashedPassword);
    }

    private void setCsrfAuthentication(
            GetUniqueClientResponse getUniqueClientResponse,
            HttpServletRequest request
    ) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        getUniqueClientResponse.getClient().getUsername(),
                        null,
                        List.of(new SimpleGrantedAuthority("CLIENT"))
                )
        );

        request.getSession(true).setAttribute(
                HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                SecurityContextHolder.getContext()
        );
    }

    public AuthLoginResponse confirmLogin(
            GetUniqueClientResponse getUniqueClientResponse,
            LoginAuthPayload loginAuthPayload,
            HttpServletRequest request
    ) {
        if(verifyPassword(loginAuthPayload.getPassword(), getUniqueClientResponse.getClient().getPassword())) {
            setCsrfAuthentication(getUniqueClientResponse, request);
            return new AuthLoginSuccessResponse(jwtProvider.generateToken(JSONHelper.createJSONObject(getUniqueClientResponse.getClient())));

        }
        else return new AuthLoginWrongPasswordErrorResponse();
    }
}
