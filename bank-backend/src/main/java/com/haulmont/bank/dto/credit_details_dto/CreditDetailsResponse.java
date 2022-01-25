package com.haulmont.bank.dto.credit_details_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haulmont.bank.dto.credit_offer_dto.responses.CreditOfferResponse;
import com.haulmont.bank.models.CreditDetails;
import com.haulmont.bank.models.CreditOffer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDetailsResponse {

    private UUID id;
    private BigDecimal creditLimit;
    private BigDecimal creditPercent;
    private UUID bankId;
    private List<CreditOfferResponse> creditOffers;

    public static CreditDetailsResponse convertToResponse(CreditDetails creditDetails) {

        return new CreditDetailsResponse(
                creditDetails.getId(),
                creditDetails.getCreditLimit(),
                creditDetails.getCreditPercent(),
                creditDetails.getBank().getId(),
                getCreditOfferInDto(creditDetails.getCreditOffers())
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
