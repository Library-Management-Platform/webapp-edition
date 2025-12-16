package com.platform.libraryManager.utils.factories;

import com.platform.libraryManager.utils.helpers.DateHelper;
import com.platform.libraryManager.dataAccess.models.Client;
import com.platform.libraryManager.dto.payloads.client.CreateClientPayload;

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
