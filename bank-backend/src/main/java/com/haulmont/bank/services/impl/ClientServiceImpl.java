package com.haulmont.bank.services.impl;


import com.haulmont.bank.dto.client_dto.ClientRequest;
import com.haulmont.bank.dto.client_dto.ClientRequestWithoutId;
import com.haulmont.bank.exceptions.EntityNotFoundException;
import com.haulmont.bank.models.Bank;
import com.haulmont.bank.models.Client;
import com.haulmont.bank.repositories.BankRepository;
import com.haulmont.bank.repositories.ClientRepository;
import com.haulmont.bank.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    @Override
    public Optional<Client> getClient(UUID id) {
        return clientRepository.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client updateClient(ClientRequest request) {

        Optional<Bank> bankOptional = bankRepository.findById(request.getBankId());

        if (bankOptional.isEmpty()) {
            throw new EntityNotFoundException("Bank value is empty");
        }

        Client entity;

        entity = clientRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find client with this id"));

        entity.setEmail(request.getEmail().trim());
        entity.setPassportNumber(request.getPassportNumber());
        entity.setTelephoneNumber(request.getTelephoneNumber());
        entity.setBank(bankOptional.get());

        clientRepository.save(entity);
        log.info("Client {} has been updated", entity.getId());

        return entity;
    }

    @Override
    public Client createClient(ClientRequestWithoutId request) {

        Optional<Bank> bankOptional = bankRepository.findById(request.getBankId());

        if (bankOptional.isEmpty()) {
            throw new EntityNotFoundException("Bank value is empty");
        }

        Client entity;

        entity = new Client();

        entity.setEmail(request.getEmail().trim());
        entity.setPassportNumber(request.getPassportNumber());
        entity.setTelephoneNumber(request.getTelephoneNumber());
        entity.setBank(bankOptional.get());

        clientRepository.save(entity);
        log.info("Client {} has been created", entity.getId());

        return entity;
    }

    @Override
    public void deleteClient(UUID id) {

        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            log.info("Client {} has been deleted", id);
        } else {
            log.info("Client with this id does not exist");
        }
    }
}
