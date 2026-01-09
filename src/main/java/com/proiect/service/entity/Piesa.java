package com.proiect.service.entity;

/** Clasa Entitate care definește structura datelor pentru Piesă
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Piesa")
public class Piesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PiesaID")
    private Integer piesaID;

    @Column(name = "Denumire")
    private String denumire;

    @Column(name = "Producator")
    private String producator;

    @Column(name = "Stoc")
    private Integer stoc;

    @Column(name = "Pret")
    private Double pret;
}