package com.proiect.service.entity;

/** Clasa Entitate care definește structura datelor pentru Angajat
 * @author Vrabie Tudor-Ștefan
 * @version 110 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "Angajat")
public class Angajat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AngajatID")
    private Integer angajatID;

    @Column(name = "Nume")
    private String nume;

    @Column(name = "Prenume")
    private String prenume;

    @Column(name = "Functie")
    private String functie;

    @Column(name = "Telefon")
    private String telefon;

    @Column(name = "Email")
    private String email;

    @Column(name = "DataAngajare")
    private LocalDate dataAngajare;
}