package com.platform.libraryManager.dto.searchQueryParams;


import java.time.LocalDateTime;

public class AdminSearchQueryParams extends UserSearchQueryParams {


    public AdminSearchQueryParams(
            Long id,
            String username,
            String email,
            String password,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        super(id, username, email, password, createdAt, updatedAt);
    }

}
