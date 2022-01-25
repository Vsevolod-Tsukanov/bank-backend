package com.haulmont.bank.services;

import com.haulmont.bank.dto.credit_offer_dto.requests.CreditOfferRequest;
import com.haulmont.bank.dto.credit_offer_dto.requests.CreditOfferRequestWithoutId;
import com.haulmont.bank.models.CreditOffer;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditOffersService {

    Optional<CreditOffer> getCreditOffer(UUID id);

    List<CreditOffer> getAllCreditOffers();

    CreditOffer createCreditOffer(CreditOfferRequestWithoutId request);

    CreditOffer updateCreditOffer(CreditOfferRequest request);

    void deleteCreditOffer(UUID id);
}
