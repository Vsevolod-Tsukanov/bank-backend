package com.haulmont.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "payment_schedule")
@Getter
@Setter
@NoArgsConstructor
public class PaymentSchedule {

    @Id
    @Column(name = "payment_schedule_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "date_of_first_payment")
    private Date dateOfFirstPayment;

    @Column(name = "date_of_last_payment")
    private Date dateOfLastPayment;

    @Column(name = "sum_of_monthly_payment")
    private BigDecimal sumOfMonthlyPayment;

    @Column(name = "sum_of_payment")
    private BigDecimal sumOfPayment;

    @Column(name = "sum_of_principal")
    private BigDecimal sumOfPrincipal;

    @Column(name = "sum_of_percent")
    private BigDecimal sumOfPercent;

    @OneToOne(targetEntity = CreditOffer.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "paymentSchedule")
    private CreditOffer creditOffer;

}
