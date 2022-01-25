package com.haulmont.bank.dto.credit_offer_dto.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditOfferRequest extends AbstractCreditOfferRequest {
    private UUID id;
}
