package com.haulmont.bank.dto.credit_offer_dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractCreditOfferRequest {

    private UUID clientId;
    private UUID creditId;
    private UUID paymentScheduleId;
    private BigDecimal sumOfCredit;
    private Integer monthsOfCredit;
}
