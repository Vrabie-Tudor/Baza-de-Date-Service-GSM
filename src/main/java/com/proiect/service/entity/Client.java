package com.proiect.service.entity;

/** Clasa Entitate care definește structura datelor pentru Client

 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025

 */

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ClientID")
    private Integer clientID;

    @Column(name = "Nume")
    @NotBlank(message = "Numele este obligatoriu")
    @Size(min = 3, max = 50, message = "Numele trebuie să aibă între 3 și 50 de caractere")
    private String nume;

    @Column(name = "Prenume")
    @NotBlank(message = "Prenumele este obligatoriu")
    @Size(min = 3, max = 50, message = "Prenumele trebuie să aibă între 3 și 50 de caractere")
    private String prenume;

    @Column(name = "Telefon")
    @Pattern(regexp = "^07[0-9]{8}$", message = "Numărul de telefon invalid (ex: 0722123456)")
    private String telefon;

    @Column(name = "Email")
    @Email(message = "Formatul email-ului este invalid")
    private String email;

    @Column(name = "Adresa")
    @Size(max = 100, message = "Adresa este prea lungă")
    private String adresa;
}