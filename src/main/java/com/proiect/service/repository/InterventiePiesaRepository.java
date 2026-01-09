package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela de legătură Interventie-Piesa
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.InterventiePiesa;
import com.proiect.service.entity.InterventiePiesaKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InterventiePiesaRepository extends JpaRepository<InterventiePiesa, InterventiePiesaKey> {

    @Query(value = "SELECT * FROM InterventiePiesa", nativeQuery = true)
    List<InterventiePiesa> toataLista();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO InterventiePiesa (InterventieID, PiesaID, Cantitate, CostTotal) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void adauga(Integer interventieID, Integer piesaID, Integer cantitate, Double costTotal);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM InterventiePiesa WHERE InterventieID = ?1 AND PiesaID = ?2", nativeQuery = true)
    void sterge(Integer interventieID, Integer piesaID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM InterventiePiesa WHERE InterventieID = ?1", nativeQuery = true)
    void stergeToatePieseleInterventiei(Integer interventieID);
}