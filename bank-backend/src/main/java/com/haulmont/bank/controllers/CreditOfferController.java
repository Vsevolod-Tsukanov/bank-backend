package com.haulmont.bank.controllers;


import com.haulmont.bank.dto.credit_offer_dto.requests.CreditOfferRequest;
import com.haulmont.bank.dto.credit_offer_dto.requests.CreditOfferRequestWithoutId;
import com.haulmont.bank.dto.credit_offer_dto.responses.CreditOfferResponse;
import com.haulmont.bank.services.CreditOffersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "offers", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CreditOfferController {

    private final CreditOffersService creditOffersService;


    @GetMapping("/{id}")
    public CreditOfferResponse getCreditOffer(@PathVariable UUID id) {
        return creditOffersService.getCreditOffer(id)
                .map(CreditOfferResponse::convertToResponse)
                .orElse(null);
    }

    @GetMapping
    public List<CreditOfferResponse> getAllCreditOffers() {
        return creditOffersService.getAllCreditOffers()
                .stream()
                .map(CreditOfferResponse::convertToResponse)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditOfferResponse createCreditOffer(@RequestBody CreditOfferRequestWithoutId request) {
        return CreditOfferResponse.convertToResponse(creditOffersService.createCreditOffer(request));
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreditOfferResponse updateCreditOffer(@RequestBody CreditOfferRequest request) {
        return CreditOfferResponse.convertToResponse(creditOffersService.updateCreditOffer(request));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreditOffer(@PathVariable UUID id) {
        creditOffersService.deleteCreditOffer(id);
    }
}
