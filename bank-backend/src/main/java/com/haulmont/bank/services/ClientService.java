package com.haulmont.bank.services;

import com.haulmont.bank.dto.client_dto.ClientRequest;
import com.haulmont.bank.dto.client_dto.ClientRequestWithoutId;
import com.haulmont.bank.models.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientService {


    Optional<Client> getClient(UUID id);

    List<Client> getAllClients();

    Client createClient(ClientRequestWithoutId request);

    Client updateClient(ClientRequest request);

    void deleteClient(UUID id);
}
