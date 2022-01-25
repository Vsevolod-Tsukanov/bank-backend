package com.haulmont.bank.repositories;

import com.haulmont.bank.models.CreditOffer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CreditOffersRepository extends JpaRepository<CreditOffer, UUID> {
}
