package com.haulmont.bank.dto.bank_dto;


import com.haulmont.bank.dto.client_dto.ClientResponse;
import com.haulmont.bank.dto.credit_details_dto.CreditDetailsResponse;
import com.haulmont.bank.models.Bank;
import com.haulmont.bank.models.Client;
import com.haulmont.bank.models.CreditDetails;
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
public class BankResponse {

    private UUID id;
    private List<CreditDetailsResponse> credits;
    private List<ClientResponse> clients;

    public static BankResponse convertToResponse(Bank bank) {
        return new BankResponse(
                bank.getId(),
                getCreditInDto(bank.getCreditDetails()),
                getClientInDto(bank.getClients())

        );
    }

    private static List<CreditDetailsResponse> getCreditInDto(List<CreditDetails> list) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<CreditDetailsResponse> responses = new ArrayList<>();

        for (CreditDetails creditDetails : list) {
            responses.add(CreditDetailsResponse.convertToResponse(creditDetails));
        }

        return responses;
    }

    private static List<ClientResponse> getClientInDto(List<Client> list) {

        if (list == null || list.isEmpty()) {
            return Collections.emptyList();
        }

        List<ClientResponse> responses = new ArrayList<>();

        for (Client client : list) {
            responses.add(ClientResponse.convertToResponse(client));
        }

        return responses;
    }

}
