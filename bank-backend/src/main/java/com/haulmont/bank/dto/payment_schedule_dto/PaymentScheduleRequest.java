package com.haulmont.bank.dto.payment_schedule_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentScheduleRequest {
    private UUID id;
}
