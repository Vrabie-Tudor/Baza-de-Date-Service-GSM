package com.proiect.service.entity;

/** Clasa Entitate care definește structura datelor pentru Utilizator (Login)
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Utilizator")
public class Utilizator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UtilizatorID")
    private Integer utilizatorID;

    @Column(name = "Username")
    private String username;

    @Column(name = "Parola")
    private String parola;
}