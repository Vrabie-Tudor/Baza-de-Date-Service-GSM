package com.proiect.service.entity;

/** Clasa ajutătoare pentru cheia primară compusă a tabelei InterventiePiesa
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class InterventiePiesaKey implements Serializable {

    @Column(name = "InterventieID")
    private Integer interventieID;

    @Column(name = "PiesaID")
    private Integer piesaID;

    public InterventiePiesaKey() {}

    public InterventiePiesaKey(Integer interventieID, Integer piesaID) {
        this.interventieID = interventieID;
        this.piesaID = piesaID;
    }
}