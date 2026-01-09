package com.proiect.service.repository;

import com.proiect.service.entity.Interventie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatisticaRepository extends JpaRepository<Interventie, Integer> {

    // --- INTEROGARI COMPLEXE (SUBCERERI) ---

    // 1. Clienti cu dispozitive defecte (Subcerere in WHERE)
    @Query(value = "SELECT Nume + ' ' + Prenume as Rezultat FROM Client WHERE ClientID IN (SELECT ClientID FROM Dispozitiv WHERE Stare = 'Defect')", nativeQuery = true)
    List<String> clientiCuDispozitiveDefecte();

    // 2. Angajati cu manopera peste medie (Subcerere cu Agregare)
    @Query(value = "SELECT Nume + ' ' + Prenume as Rezultat FROM Angajat WHERE AngajatID IN (SELECT AngajatID FROM Interventie WHERE CostManopera > (SELECT AVG(CostManopera) FROM Interventie))", nativeQuery = true)
    List<String> angajatiTopManopera();

    // 3. Piese cu stoc critic (sub medie)
    @Query(value = "SELECT Denumire + ' (' + CAST(Stoc AS VARCHAR) + ' buc)' as Rezultat FROM Piesa WHERE Stoc < (SELECT AVG(Stoc) FROM Piesa)", nativeQuery = true)
    List<String> pieseStocCritic();

    // 4. Dispozitivul cu cea mai scumpa manopera (Subcerere TOP 1)
    @Query(value = "SELECT Producator + ' ' + Model as Rezultat FROM Dispozitiv WHERE DispozitivID = (SELECT TOP 1 DispozitivID FROM Interventie ORDER BY CostManopera DESC)", nativeQuery = true)
    List<String> dispozitivScump();


    // --- INTEROGARI SIMPLE (JOIN-uri Explicite pentru statistici) ---

    // 5. Total incasari per Angajat (JOIN + GROUP BY)
    @Query(value = "SELECT A.Nume, SUM(I.CostManopera) as Total FROM Angajat A JOIN Interventie I ON A.AngajatID = I.AngajatID GROUP BY A.Nume", nativeQuery = true)
    List<Object[]> venituriPerAngajat();

    // 6. Numar interventii per Brand Dispozitiv (JOIN + COUNT)
    @Query(value = "SELECT D.Producator, COUNT(I.InterventieID) as Nr FROM Dispozitiv D JOIN Interventie I ON D.DispozitivID = I.DispozitivID GROUP BY D.Producator", nativeQuery = true)
    List<Object[]> interventiiPerBrand();
}