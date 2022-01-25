package com.haulmont.bank.services.impl;

import com.haulmont.bank.dto.credit_details_dto.CreditDetailsRequest;
import com.haulmont.bank.dto.credit_details_dto.CreditDetailsRequestWithoutId;
import com.haulmont.bank.exceptions.EntityNotFoundException;
import com.haulmont.bank.models.Bank;
import com.haulmont.bank.models.CreditDetails;
import com.haulmont.bank.repositories.BankRepository;
import com.haulmont.bank.repositories.CreditDetailsRepository;
import com.haulmont.bank.services.CreditDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditDetailsServiceImpl implements CreditDetailsService {

    private final CreditDetailsRepository creditDetailsRepository;
    private final BankRepository bankRepository;

    @Override
    public Optional<CreditDetails> getCreditDetails(UUID id) {
        return creditDetailsRepository.findById(id);
    }

    @Override
    public List<CreditDetails> getAllCreditsDetails() {
        return creditDetailsRepository.findAll();
    }

    @Override
    public CreditDetails updateCreditDetails(CreditDetailsRequest request) {

        Optional<Bank> bankOptional = bankRepository.findById(request.getBankId());

        if (bankOptional.isEmpty()) {
            throw new EntityNotFoundException("Bank value is empty");
        }

        CreditDetails entity;


        entity = creditDetailsRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find credit details by id"));


        entity.setBank(bankOptional.get());
        entity.setCreditLimit(request.getCreditLimit());
        entity.setCreditPercent(request.getCreditPercent());

        creditDetailsRepository.save(entity);
        log.info("Credit details {} has been updated", entity.getId());

        return entity;
    }

    @Override
    public CreditDetails createCreditDetails(CreditDetailsRequestWithoutId request) {

        Optional<Bank> bankOptional = bankRepository.findById(request.getBankId());

        if (bankOptional.isEmpty()) {
            throw new EntityNotFoundException("Bank value is empty");
        }

        CreditDetails entity = new CreditDetails();

        entity.setBank(bankOptional.get());
        entity.setCreditLimit(request.getCreditLimit());
        entity.setCreditPercent(request.getCreditPercent());

        creditDetailsRepository.save(entity);
        log.info("Credit details {} has been created ", entity.getId());

        return entity;
    }

    @Override
    public void deleteCreditDetails(UUID id) {

        if (creditDetailsRepository.existsById(id)) {
            creditDetailsRepository.deleteById(id);
            log.info("Credit Details {} has been deleted", id);
        } else {
            log.info("Credit Details with this id does not exist");
        }
    }
}
