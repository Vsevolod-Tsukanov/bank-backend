package com.haulmont.bank.repositories;

import com.haulmont.bank.models.PaymentSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentScheduleRepository extends JpaRepository<PaymentSchedule, UUID> {
}
