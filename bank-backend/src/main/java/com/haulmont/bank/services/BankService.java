package com.haulmont.bank.services;

import com.haulmont.bank.models.Bank;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankService {


    Bank createBank();

    Optional<Bank> getBank(UUID id);

    List<Bank> getAllBanks();

    void deleteBank(UUID id);

}


