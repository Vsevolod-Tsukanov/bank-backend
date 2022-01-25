package com.haulmont.bank.controllers;


import com.haulmont.bank.dto.payment_schedule_dto.PaymentScheduleResponse;
import com.haulmont.bank.services.PaymentScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "schedules", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class PaymentScheduleController {


    private final PaymentScheduleService paymentScheduleService;

    @GetMapping("/{id}")
    public PaymentScheduleResponse getPaymentSchedule(@PathVariable UUID id) {
        return paymentScheduleService.getPaymentSchedule(id)
                .map(PaymentScheduleResponse::convertToResponse)
                .orElse(null);
    }

    @GetMapping
    public List<PaymentScheduleResponse> getAllPaymentSchedules() {
        return paymentScheduleService.getAllPaymentSchedules()
                .stream()
                .map(PaymentScheduleResponse::convertToResponse)
                .collect(Collectors.toList());
    }


}
