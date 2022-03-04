package com.haulmont.bank.controllers;


import com.haulmont.bank.dto.credit_details_dto.CreditDetailsRequest;
import com.haulmont.bank.dto.credit_details_dto.CreditDetailsRequestWithoutId;
import com.haulmont.bank.dto.credit_details_dto.CreditDetailsResponse;
import com.haulmont.bank.services.CreditDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/creditDetails", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CreditDetailsController {

    private final CreditDetailsService creditDetailsService;

    @GetMapping("/{id}")
    public CreditDetailsResponse getCreditDetails(@PathVariable UUID id) {
        return creditDetailsService.getCreditDetails(id)
                .map(CreditDetailsResponse::convertToResponse)
                .orElse(null);
    }

    @GetMapping
    public List<CreditDetailsResponse> getAllCreditsDetails() {
        return creditDetailsService.getAllCreditsDetails()
                .stream()
                .map(CreditDetailsResponse::convertToResponse)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditDetailsResponse createCreditDetails(@RequestBody @Valid CreditDetailsRequestWithoutId request) {
        return CreditDetailsResponse.convertToResponse(creditDetailsService.createCreditDetails(request));
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditDetailsResponse updateCreditDetails(@RequestBody @Valid CreditDetailsRequest request) {
        return CreditDetailsResponse.convertToResponse(creditDetailsService.updateCreditDetails(request));
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreditDetails(@PathVariable UUID id) {
        creditDetailsService.deleteCreditDetails(id);
    }


}
