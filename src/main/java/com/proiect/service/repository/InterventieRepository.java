package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela Intervenție
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Interventie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InterventieRepository extends JpaRepository<Interventie, Integer> {

    @Query(value = "SELECT * FROM Interventie", nativeQuery = true)
    List<Interventie> toataListaInterventii();

    @Query(value = "SELECT * FROM Interventie WHERE DescriereProblema LIKE %?1% OR Status LIKE %?1%", nativeQuery = true)
    List<Interventie> cautaInterventie(String keyword);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Interventie (DescriereProblema, SolutieAplicata, DataStart, DataFinalizare, CostManopera, Status, DispozitivID, AngajatID) VALUES (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    void adaugaInterventie(String descriere, String solutie, LocalDate dataStart, LocalDate dataFinalizare, Double cost, String status, Integer dispID, Integer angajatID);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Interventie SET DescriereProblema=?1, SolutieAplicata=?2, DataStart=?3, DataFinalizare=?4, CostManopera=?5, Status=?6, DispozitivID=?7, AngajatID=?8 WHERE InterventieID=?9", nativeQuery = true)
    void modificaInterventie(String descriere, String solutie, LocalDate dataStart, LocalDate dataFinalizare, Double cost, String status, Integer dispID, Integer angajatID, Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Interventie WHERE InterventieID = ?1", nativeQuery = true)
    void stergeInterventie(Integer id);

    @Query(value = "SELECT * FROM Interventie WHERE InterventieID = ?1", nativeQuery = true)
    Interventie gasesteDupaId(Integer id);
}