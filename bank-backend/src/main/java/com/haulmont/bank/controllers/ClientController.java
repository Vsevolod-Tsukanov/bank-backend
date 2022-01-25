package com.haulmont.bank.controllers;

import com.haulmont.bank.dto.client_dto.ClientRequest;
import com.haulmont.bank.dto.client_dto.ClientRequestWithoutId;
import com.haulmont.bank.dto.client_dto.ClientResponse;
import com.haulmont.bank.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "clients", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/{id}")
    public ClientResponse getClient(@PathVariable UUID id) {
        return clientService.getClient(id)
                .map(ClientResponse::convertToResponse)
                .orElse(null);
    }

    @GetMapping
    public List<ClientResponse> getAllClients() {
        return clientService.getAllClients()
                .stream()
                .map(ClientResponse::convertToResponse)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientResponse createClient(@RequestBody ClientRequestWithoutId request) {
        return ClientResponse.convertToResponse(clientService.createClient(request));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ClientResponse updateClient(@RequestBody ClientRequest request) {
        return ClientResponse.convertToResponse(clientService.updateClient(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
    }
}
