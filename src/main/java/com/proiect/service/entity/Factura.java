package com.proiect.service.entity;

/** Clasa Entitate pentru Facturi
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Factura")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FacturaID")
    private Integer facturaID;

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Client client;

    @OneToOne
    @JoinColumn(name = "InterventieID", unique = true)
    private Interventie interventie;

    @Column(name = "SumaTotala")
    private Double sumaTotala;

    @Column(name = "DataEmitere")
    private LocalDate dataEmitere;

    @Column(name = "Profit")
    private Double profit;
}