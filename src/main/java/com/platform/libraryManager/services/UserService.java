package com.platform.libraryManager.services;


import com.platform.libraryManager.models.User;
import com.platform.libraryManager.repositories.UserRepository;

import com.platform.libraryManager.payloads.user.GetUniqueUserPayload;
import com.platform.libraryManager.responses.endpoints.user.getUnique.GetUniqueUserErrorResponse;
import com.platform.libraryManager.responses.endpoints.user.getUnique.GetUniqueUserResponse;


import com.platform.libraryManager.responses.endpoints.user.getUnique.GetUniqueUserSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired private UserRepository userRepository;


    public GetUniqueUserResponse getUniqueUser(GetUniqueUserPayload getUniqueUserPayload) {

        try {
            final User user = userRepository.findByUsername(
                    getUniqueUserPayload.getUsername()
            ).get();

            return new GetUniqueUserSuccessResponse(user);

        }catch (Exception exception) {
            return new GetUniqueUserErrorResponse();
        }

    }


    public void verifyUser(User user) {
        user.verify();
        userRepository.save(user);
    }

}
