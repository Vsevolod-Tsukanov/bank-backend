package com.haulmont.bank.controllers;


import com.haulmont.bank.dto.bank_dto.BankResponse;
import com.haulmont.bank.services.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/banks", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping("/{id}")
    public BankResponse getBank(@PathVariable UUID id) {
        return bankService.getBank(id)
                .map(BankResponse::convertToResponse)
                .orElse(null);

    }

    @GetMapping
    public List<BankResponse> getAllBanks() {
        return bankService.getAllBanks()
                .stream()
                .map(BankResponse::convertToResponse)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BankResponse createBank() {
        return BankResponse.convertToResponse(bankService.createBank());
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBank(@PathVariable UUID id) {
        bankService.deleteBank(id);
    }
}
