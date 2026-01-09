package com.proiect.service.entity;

/** Clasa Entitate care definește structura datelor pentru Intervenție
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Interventie")
public class Interventie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "InterventieID")
    private Integer interventieID;

    @Column(name = "DescriereProblema")
    private String descriereProblema;

    @Column(name = "SolutieAplicata")
    private String solutieAplicata;

    @Column(name = "DataStart")
    private LocalDate dataStart;

    @Column(name = "DataFinalizare")
    private LocalDate dataFinalizare;

    @Column(name = "CostManopera")
    private Double costManopera;

    @Column(name = "Status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "DispozitivID")
    private Dispozitiv dispozitiv;

    @ManyToOne
    @JoinColumn(name = "AngajatID")
    private Angajat angajat;
}