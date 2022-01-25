package com.haulmont.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
public class Client {

    @Id
    @Column(name = "client_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "telephone_number")
    private Long telephoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "passport_number")
    private Long passportNumber;

    @ManyToOne(targetEntity = Bank.class,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "client")
    private List<CreditOffer> creditOffers;

}
