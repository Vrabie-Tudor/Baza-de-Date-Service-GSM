package com.proiect.service.entity;

/** Clasa Entitate pentru tabela de legătură dintre Intervenție și Piesă
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "InterventiePiesa")
public class InterventiePiesa {

    @EmbeddedId
    private InterventiePiesaKey id;

    @ManyToOne
    @MapsId("interventieID")
    @JoinColumn(name = "InterventieID")
    private Interventie interventie;

    @ManyToOne
    @MapsId("piesaID")
    @JoinColumn(name = "PiesaID")
    private Piesa piesa;

    @Column(name = "Cantitate")
    private Integer cantitate;

    @Column(name = "CostTotal")
    private Double costTotal;
}