package com.haulmont.bank.services.impl;

import com.haulmont.bank.models.PaymentSchedule;
import com.haulmont.bank.repositories.PaymentScheduleRepository;
import com.haulmont.bank.services.PaymentScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentScheduleServiceImpl implements PaymentScheduleService {

    private final PaymentScheduleRepository paymentScheduleRepository;

    @Override
    public Optional<PaymentSchedule> getPaymentSchedule(UUID id) {
        return paymentScheduleRepository.findById(id);
    }

    @Override
    public List<PaymentSchedule> getAllPaymentSchedules() {
        return paymentScheduleRepository.findAll();
    }

}
