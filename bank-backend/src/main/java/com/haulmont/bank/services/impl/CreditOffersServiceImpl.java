package com.haulmont.bank.services.impl;


import com.haulmont.bank.dto.credit_offer_dto.requests.AbstractCreditOfferRequest;
import com.haulmont.bank.dto.credit_offer_dto.requests.CreditOfferRequest;
import com.haulmont.bank.dto.credit_offer_dto.requests.CreditOfferRequestWithoutId;
import com.haulmont.bank.exceptions.EntityNotFoundException;
import com.haulmont.bank.exceptions.OverLimitException;
import com.haulmont.bank.models.Client;
import com.haulmont.bank.models.CreditDetails;
import com.haulmont.bank.models.CreditOffer;
import com.haulmont.bank.models.PaymentSchedule;
import com.haulmont.bank.repositories.ClientRepository;
import com.haulmont.bank.repositories.CreditDetailsRepository;
import com.haulmont.bank.repositories.CreditOffersRepository;
import com.haulmont.bank.repositories.PaymentScheduleRepository;
import com.haulmont.bank.services.CreditOffersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditOffersServiceImpl implements CreditOffersService {

    private final CreditOffersRepository creditOffersRepository;
    private final ClientRepository clientRepository;
    private final CreditDetailsRepository creditDetailsRepository;
    private final PaymentScheduleRepository paymentScheduleRepository;


    @Override
    public Optional<CreditOffer> getCreditOffer(UUID id) {

        return creditOffersRepository.findById(id);
    }

    @Override
    public List<CreditOffer> getAllCreditOffers() {
        return creditOffersRepository.findAll();
    }

    @Override
    public CreditOffer updateCreditOffer(CreditOfferRequest request) {


        Optional<Client> clientOptional = clientRepository.findById(request.getClientId());

        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client value is empty");
        }

        Optional<CreditDetails> creditDetailsOptional = creditDetailsRepository.findById(request.getCreditId());
        PaymentSchedule schedule = paymentScheduleRepository.findById(request.getPaymentScheduleId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find Payment Schedule by id"));


        if (creditDetailsOptional.isEmpty()) {
            throw new EntityNotFoundException("Credit Details value is empty");
        }


        CreditOffer entity;

        PaymentSchedule generatedSchedule = generatePaymentSchedule(request);


        entity = creditOffersRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Cannot find credit offer by id"));

        schedule.setDateOfFirstPayment(generatedSchedule.getDateOfFirstPayment());
        schedule.setDateOfLastPayment(generatedSchedule.getDateOfLastPayment());
        schedule.setSumOfPayment(generatedSchedule.getSumOfPayment());
        schedule.setSumOfMonthlyPayment(generatedSchedule.getSumOfMonthlyPayment());
        schedule.setSumOfPercent(generatedSchedule.getSumOfPercent());
        schedule.setSumOfPrincipal(generatedSchedule.getSumOfPrincipal());
//        schedule.setId(request.getPaymentScheduleId());

        entity.setClient(clientOptional.get());
        entity.setCreditDetails(creditDetailsOptional.get());
        entity.setMonthsOfCredit(request.getMonthsOfCredit());
        entity.setPaymentSchedule(schedule);
        entity.setSumOfCredit(request.getSumOfCredit());

        schedule.setCreditOffer(entity);

        if (isCreditLimitExceeded(request, entity)) {
            throw new OverLimitException("Over Credit Limit");
        }

//        paymentScheduleRepository.save(schedule);
        creditOffersRepository.save(entity);
        log.info("Credit Offer {} has been created or updated", entity.getId());

        return entity;
    }

    @Override
    public CreditOffer createCreditOffer(CreditOfferRequestWithoutId request) {


        Optional<Client> clientOptional = clientRepository.findById(request.getClientId());

        if (clientOptional.isEmpty()) {
            throw new EntityNotFoundException("Client value is empty");
        }

        Optional<CreditDetails> creditDetailsOptional = creditDetailsRepository.findById(request.getCreditId());

        if (creditDetailsOptional.isEmpty()) {
            throw new EntityNotFoundException("Credit details value is empty");
        }

        CreditOffer entity;

        entity = new CreditOffer();

        entity.setClient(clientOptional.get());
        entity.setCreditDetails(creditDetailsOptional.get());
        entity.setMonthsOfCredit(request.getMonthsOfCredit());
        entity.setPaymentSchedule(generatePaymentSchedule(request));
        entity.setSumOfCredit(request.getSumOfCredit());

        if (isCreditLimitExceeded(request, entity)) {
            throw new OverLimitException("Over Credit Limit");
        }


        creditOffersRepository.save(entity);
        log.info("Credit Offer {} has been created", entity.getId());

        return entity;
    }

    @Override
    public void deleteCreditOffer(UUID id) {
        if (creditOffersRepository.existsById(id)) {
            creditOffersRepository.deleteById(id);
            log.info("Credit Offer {} has been deleted", id);
        } else {
            log.info("Credit Offer with this id does not exist");
        }
    }

    private PaymentSchedule generatePaymentSchedule(AbstractCreditOfferRequest request) {

        PaymentSchedule schedule = new PaymentSchedule();

        BigDecimal sumOfPrincipal = request.getSumOfCredit();

        BigDecimal percents = creditDetailsRepository.getById(request.getCreditId()).getCreditPercent();
        BigDecimal sumOfPercent = sumOfPrincipal.multiply(percents.divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_EVEN));

        BigDecimal sumOfPayment = sumOfPrincipal.add(sumOfPercent);
        BigDecimal monthlyPayment = sumOfPayment.divide(BigDecimal.valueOf(request.getMonthsOfCredit()), 2, RoundingMode.HALF_EVEN);

        Date firstPayment = DateUtils.addMonths(new Date(), 1);
        Date lastPayment = DateUtils.addMonths(firstPayment, request.getMonthsOfCredit() - 1);

        schedule.setSumOfPrincipal(sumOfPrincipal);
        schedule.setSumOfPercent(sumOfPercent);
        schedule.setSumOfPayment(sumOfPayment);
        schedule.setSumOfMonthlyPayment(monthlyPayment);
        schedule.setDateOfFirstPayment(firstPayment);
        schedule.setDateOfLastPayment(lastPayment);

//        paymentScheduleRepository.save(schedule);
        System.out.println("a");
        return schedule;
    }

    private boolean isCreditLimitExceeded(AbstractCreditOfferRequest request, CreditOffer offer) {

        BigDecimal sumOfCredit = request.getSumOfCredit();
        BigDecimal creditLimit = offer.getCreditDetails().getCreditLimit();


        int resultOfComparing = sumOfCredit.compareTo(creditLimit.add(BigDecimal.valueOf(1)));

        if (resultOfComparing == 1) {
            return true;
        } else {

            if (resultOfComparing == -1) {
                return false;
            }
        }
        return true;
    }
}
