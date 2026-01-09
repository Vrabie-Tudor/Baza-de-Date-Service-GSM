package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela Angajat
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Angajat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Integer> {

    @Query(value = "SELECT * FROM Angajat", nativeQuery = true)
    List<Angajat> toataListaAngajati();

    @Query(value = "SELECT * FROM Angajat WHERE Nume LIKE %?1% OR Prenume LIKE %?1% OR Functie LIKE %?1% OR Email LIKE %?1%", nativeQuery = true)
    List<Angajat> cautaAngajat(String keyword);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Angajat (Nume, Prenume, Functie, Telefon, Email, DataAngajare) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void adaugaAngajat(String nume, String prenume, String functie, String telefon, String email, LocalDate dataAngajare);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Angajat WHERE AngajatID = ?1", nativeQuery = true)
    void stergeAngajat(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Angajat SET Nume=?1, Prenume=?2, Functie=?3, Telefon=?4, Email=?5, DataAngajare=?6 WHERE AngajatID=?7", nativeQuery = true)
    void modificaAngajat(String nume, String prenume, String functie, String telefon, String email, LocalDate dataAngajare, Integer id);

    @Query(value = "SELECT * FROM Angajat WHERE AngajatID = ?1", nativeQuery = true)
    Angajat gasesteDupaId(Integer id);
}