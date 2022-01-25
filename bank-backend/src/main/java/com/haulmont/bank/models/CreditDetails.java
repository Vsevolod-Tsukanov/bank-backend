package com.haulmont.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "credit_details")
@Getter
@Setter
@NoArgsConstructor
public class CreditDetails {

    @Id
    @Column(name = "credit_details_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "credit_percent")
    private BigDecimal creditPercent;

    @ManyToOne(targetEntity = Bank.class, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "creditDetails")
    List<CreditOffer> creditOffers;
}
