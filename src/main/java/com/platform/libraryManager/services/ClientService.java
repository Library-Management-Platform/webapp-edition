package com.platform.libraryManager.services;

import com.platform.libraryManager.payloads.clientPayloads.GetUniqueClientPayload;
import com.platform.libraryManager.responses.endpoints.client.create.CreateClientErrorResponse;
import com.platform.libraryManager.responses.endpoints.client.create.CreateClientResponse;
import com.platform.libraryManager.responses.endpoints.client.create.CreateClientSuccessResponse;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientErrorResponse;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientResponse;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientSuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.repositories.ClientRespository;


@Service
public class ClientService {

    @Autowired ClientRespository clientRespository;

    public GetUniqueClientResponse getUniqueClient(GetUniqueClientPayload getUniqueClientPayload) {

        try {
            final Client client = clientRespository.findByUsername(
                    getUniqueClientPayload.getUsername()
            ).get();

            return new GetUniqueClientSuccessResponse(client);

        }catch (Exception exception) {
            return new GetUniqueClientErrorResponse();
        }

    }


    public CreateClientResponse createClient(CreateClientPayload createClientPayload) {

        try {
            final Client client = ClientFactory.create(createClientPayload);
            clientRespository.save(client);
            return new CreateClientSuccessResponse();

        }catch(DataIntegrityViolationException exception) {
            return new CreateClientErrorResponse(409, "Client Already Exists");

        }catch(Exception exception) {
            return new CreateClientErrorResponse(400, "Invalid Client data format.");

        }
    }


}
