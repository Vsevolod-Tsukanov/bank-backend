package com.haulmont.bank.dto.client_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequestWithoutId {
    private Long telephoneNumber;
    private String email;
    private Long passportNumber;
    private UUID bankId;
}
