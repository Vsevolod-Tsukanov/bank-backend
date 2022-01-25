package com.haulmont.bank.services;

import com.haulmont.bank.dto.credit_details_dto.CreditDetailsRequest;
import com.haulmont.bank.dto.credit_details_dto.CreditDetailsRequestWithoutId;
import com.haulmont.bank.models.CreditDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CreditDetailsService {

    Optional<CreditDetails> getCreditDetails(UUID id);

    List<CreditDetails> getAllCreditsDetails();

    CreditDetails createCreditDetails(CreditDetailsRequestWithoutId request);

    CreditDetails updateCreditDetails(CreditDetailsRequest request);

    void deleteCreditDetails(UUID id);
}
