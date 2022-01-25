package com.haulmont.bank.dto.credit_offer_dto.requests;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditOfferRequestWithoutId extends AbstractCreditOfferRequest {

}
