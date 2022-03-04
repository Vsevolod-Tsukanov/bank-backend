package com.haulmont.bank.dto.credit_details_dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDetailsRequest {

    private UUID id;
    @DecimalMax("10000000.0")
    private BigDecimal creditLimit;
    @DecimalMin("0.0")
    @DecimalMax("20.0")
    private BigDecimal creditPercent;
    private UUID bankId;
}
