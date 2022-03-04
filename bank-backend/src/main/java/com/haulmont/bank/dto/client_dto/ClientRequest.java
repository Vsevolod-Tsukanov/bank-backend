package com.haulmont.bank.dto.client_dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Email;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientRequest {
    private UUID id;
    private Long telephoneNumber;
    @Email
    private String email;
    private Long passportNumber;
    private UUID bankId;
}
