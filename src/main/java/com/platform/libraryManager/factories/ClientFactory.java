package com.platform.libraryManager.factories;

import com.platform.libraryManager.helpers.DateHelper;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.payloads.client.CreateClientPayload;

public abstract class ClientFactory {

    public static Client createEmpty() { return new Client(); }

    public static Client create(CreateClientPayload createClientPayload) {

        return new Client(
                createClientPayload.getUsername(),
                createClientPayload.getEmail(),
                createClientPayload.getPassword(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getCurrentLocalDateTime()
        );
    }
}
