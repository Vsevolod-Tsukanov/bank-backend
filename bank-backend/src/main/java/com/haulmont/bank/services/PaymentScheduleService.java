package com.haulmont.bank.services;

import com.haulmont.bank.models.PaymentSchedule;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentScheduleService {

    Optional<PaymentSchedule> getPaymentSchedule(UUID id);

    List<PaymentSchedule> getAllPaymentSchedules();
}

