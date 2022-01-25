package com.haulmont.bank.repositories;

import com.haulmont.bank.models.CreditDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditDetailsRepository extends JpaRepository<CreditDetails, UUID> {
}
