package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela Client
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Query(value = "SELECT * FROM Client", nativeQuery = true)
    List<Client> toataListaClienti();

    @Query(value = "SELECT * FROM Client WHERE Nume LIKE %?1% OR Prenume LIKE %?1% OR Telefon LIKE %?1% OR Email LIKE %?1%", nativeQuery = true)
    List<Client> cautaClient(String keyword);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Client (Nume, Prenume, Telefon, Email, Adresa) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    void adaugaClient(String nume, String prenume, String telefon, String email, String adresa);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Client WHERE ClientID = ?1", nativeQuery = true)
    void stergeClient(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Client SET Nume=?1, Prenume=?2, Telefon=?3, Email=?4, Adresa=?5 WHERE ClientID=?6", nativeQuery = true)
    void modificaClient(String nume, String prenume, String telefon, String email, String adresa, Integer id);

    @Query(value = "SELECT * FROM Client WHERE ClientID = ?1", nativeQuery = true)
    Client gasesteDupaId(Integer id);
}