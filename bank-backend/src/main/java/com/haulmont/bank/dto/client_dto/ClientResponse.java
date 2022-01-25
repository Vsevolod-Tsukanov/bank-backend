package com.haulmont.bank.dto.client_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haulmont.bank.dto.credit_offer_dto.responses.CreditOfferResponse;
import com.haulmont.bank.models.Client;
import com.haulmont.bank.models.CreditOffer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {

    private UUID id;
    private Long telephoneNumber;
    private String email;
    private Long passportNumber;
    private UUID bankId;
    private List<CreditOfferResponse> creditOffers;

    public static ClientResponse convertToResponse(Client client) {

        return new ClientResponse(
                client.getId(),
                client.getTelephoneNumber(),
                client.getEmail(),
                client.getPassportNumber(),
                client.getBank().getId(),
                getCreditOfferInDto(client.getCreditOffers())
        );
    }

    private static List<CreditOfferResponse> getCreditOfferInDto(List<CreditOffer> list) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<CreditOfferResponse> responses = new ArrayList<>();

        for (CreditOffer creditOffer : list) {
            responses.add(CreditOfferResponse.convertToResponse(creditOffer));
        }

        return responses;
    }

}
