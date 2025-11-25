package com.platform.libraryManager.services;

import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.repositories.ClientRespository;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientExistsErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientInvalidDataEnteredErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientSuccessResponse;


@Service
public class ClientService {

    @Autowired ClientRespository clientRespository;

    public CreateClientResponse createClient(CreateClientPayload createClientPayload) {

        try {
            final Client client = ClientFactory.create(createClientPayload);
            clientRespository.save(client);
            return new CreateClientSuccessResponse();

        }catch(DataIntegrityViolationException exception) {
            return new CreateClientExistsErrorResponse();

        }catch(Exception exception) {
            return new CreateClientInvalidDataEnteredErrorResponse();
        }
    }


}
