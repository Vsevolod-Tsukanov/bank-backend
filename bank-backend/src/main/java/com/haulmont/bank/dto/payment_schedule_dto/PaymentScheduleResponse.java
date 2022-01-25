package com.haulmont.bank.dto.payment_schedule_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.haulmont.bank.dto.credit_offer_dto.responses.CreditOfferResponse;
import com.haulmont.bank.models.PaymentSchedule;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentScheduleResponse {

    private UUID id;
    private Date dateOfFirstPayment;
    private Date dateOfLastPayment;
    private BigDecimal sumOfMonthlyPayment;
    private BigDecimal sumOfPayment;
    private BigDecimal sumOfPrincipal;
    private BigDecimal sumOfPercent;
    private CreditOfferResponse creditOffer;

    public static PaymentScheduleResponse convertToResponse(PaymentSchedule paymentSchedule) {

        return new PaymentScheduleResponse(
                paymentSchedule.getId(),
                paymentSchedule.getDateOfFirstPayment(),
                paymentSchedule.getDateOfLastPayment(),
                paymentSchedule.getSumOfMonthlyPayment(),
                paymentSchedule.getSumOfPayment(),
                paymentSchedule.getSumOfPrincipal(),
                paymentSchedule.getSumOfPercent(),
                CreditOfferResponse.convertToResponse(paymentSchedule.getCreditOffer())
        );
    }


}
