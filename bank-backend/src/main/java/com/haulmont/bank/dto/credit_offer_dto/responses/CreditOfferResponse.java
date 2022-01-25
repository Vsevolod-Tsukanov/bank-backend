package com.haulmont.bank.dto.credit_offer_dto.responses;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haulmont.bank.models.CreditOffer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditOfferResponse {

    private UUID id;
    private UUID clientId;
    private UUID creditId;
    private UUID paymentScheduleId;
    private BigDecimal sumOfCredit;
    private Integer monthsOfCredit;

    public static CreditOfferResponse convertToResponse(CreditOffer creditOffer) {

        return new CreditOfferResponse(
                creditOffer.getId(),
                creditOffer.getClient().getId(),
                creditOffer.getCreditDetails().getId(),
                creditOffer.getPaymentSchedule().getId(),
                creditOffer.getSumOfCredit(),
                creditOffer.getMonthsOfCredit()
        );
    }
}
