package com.haulmont.bank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "banks")
@Getter
@Setter
@NoArgsConstructor
public class Bank {

    @Id
    @Column(name = "bank_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "bank", fetch = FetchType.LAZY)
    private List<CreditDetails> creditDetails = new ArrayList<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL,
            mappedBy = "bank", fetch = FetchType.LAZY)
    private List<Client> clients = new ArrayList<>();
}
