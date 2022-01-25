package com.haulmont.bank.services.impl;

import com.haulmont.bank.models.Bank;
import com.haulmont.bank.repositories.BankRepository;
import com.haulmont.bank.services.BankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;


    @Override
    public Optional<Bank> getBank(UUID id) {
        return bankRepository.findById(id);
    }

    @Override
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @Override
    @Transactional
    public Bank createBank() {
        Bank entity = new Bank();

        bankRepository.save(entity);
        log.info("Bank {} has been created", entity.getId());
        return entity;
    }

    @Override
    public void deleteBank(UUID id) {
        bankRepository.deleteById(id);
        log.info("Bank {} has been deleted", id);
    }


}
