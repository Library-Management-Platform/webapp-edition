package com.platform.libraryManager.shared.providers;

import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordHashingProvider {

    private final PasswordEncoder passwordEncoder;

    public PasswordHashingProvider(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    public String hash(String password) { return passwordEncoder.encode(password);}

    public boolean verify(String password, String hash) { return passwordEncoder.matches(password, hash); }
}
