package com.platform.libraryManager.services;

import com.platform.libraryManager.payloads.clientPayloads.GetUniqueClientPayload;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.createClientResponses.CreateClientResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses.GetUniqueClientErrorResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses.GetUniqueClientResponse;
import com.platform.libraryManager.responses.endpointResponses.clientResponses.getUniqueClientResponses.GetUniqueClientSuccessResponse;
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
            return new CreateClientExistsErrorResponse();

        }catch(Exception exception) {
            return new CreateClientInvalidDataEnteredErrorResponse();
        }
    }


}
