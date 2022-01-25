package com.haulmont.bank.dto.credit_details_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditDetailsRequestWithoutId {
    private BigDecimal creditLimit;
    private BigDecimal creditPercent;
    private UUID bankId;
}
