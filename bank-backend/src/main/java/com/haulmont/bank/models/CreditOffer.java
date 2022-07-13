package com.haulmont.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "credit_offer")
@Getter
@Setter
@NoArgsConstructor
public class CreditOffer {

    @Id
    @Column(name = "credit_offer_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(targetEntity = Client.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(targetEntity = CreditDetails.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "credit_details_id")
    private CreditDetails creditDetails;

    @Column(name = "sum_of_credit")
    private BigDecimal sumOfCredit;

    @Column(name = "months_of_credit")
    private Integer monthsOfCredit;

    @OneToOne(targetEntity = PaymentSchedule.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_schedule_id")
    private PaymentSchedule paymentSchedule;


}
