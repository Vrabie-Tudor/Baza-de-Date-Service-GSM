package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela Dispozitiv
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Dispozitiv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DispozitivRepository extends JpaRepository<Dispozitiv, Integer> {

    @Query(value = "SELECT * FROM Dispozitiv", nativeQuery = true)
    List<Dispozitiv> toataListaDispozitive();

    @Query(value = "SELECT * FROM Dispozitiv WHERE TipDispozitiv LIKE %?1% OR Producator LIKE %?1% OR Model LIKE %?1% OR Serie LIKE %?1%", nativeQuery = true)
    List<Dispozitiv> cautaDispozitiv(String keyword);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Dispozitiv (TipDispozitiv, Producator, Model, Serie, Stare, ClientID) VALUES (?1, ?2, ?3, ?4, ?5, ?6)", nativeQuery = true)
    void adaugaDispozitiv(String tip, String producator, String model, String serie, String stare, Integer clientID);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Dispozitiv WHERE DispozitivID = ?1", nativeQuery = true)
    void stergeDispozitiv(Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Dispozitiv SET TipDispozitiv=?1, Producator=?2, Model=?3, Serie=?4, Stare=?5, ClientID=?6 WHERE DispozitivID=?7", nativeQuery = true)
    void modificaDispozitiv(String tip, String producator, String model, String serie, String stare, Integer clientID, Integer id);

    @Query(value = "SELECT * FROM Dispozitiv WHERE DispozitivID = ?1", nativeQuery = true)
    Dispozitiv gasesteDupaId(Integer id);
}