package com.platform.libraryManager.services;


import com.platform.libraryManager.factories.ClientFactory;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.payloads.clientPayloads.CreateClientPayload;
import com.platform.libraryManager.repositories.ClientRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    @Autowired ClientRespository clientRespository;

    public Client createClient(CreateClientPayload createClientPayload) {

        try {
            final Client client = ClientFactory.create(createClientPayload);
            clientRespository.save(client);
            return client;

        }catch(Exception exception) {
            System.out.println(exception);
            return ClientFactory.createEmpty();
        }
    }


}
