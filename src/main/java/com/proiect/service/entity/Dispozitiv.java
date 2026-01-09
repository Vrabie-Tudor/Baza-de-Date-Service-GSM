package com.proiect.service.entity;

/** Clasa Entitate care definește structura datelor pentru Dispozitiv
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Dispozitiv")
public class Dispozitiv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DispozitivID")
    private Integer dispozitivID;

    @Column(name = "TipDispozitiv")
    private String tipDispozitiv;

    @Column(name = "Producator")
    private String producator;

    @Column(name = "Model")
    private String model;

    @Column(name = "Serie")
    private String serie;

    @Column(name = "Stare")
    private String stare;

    @ManyToOne
    @JoinColumn(name = "ClientID")
    private Client client;
}