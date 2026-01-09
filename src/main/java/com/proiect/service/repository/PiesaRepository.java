package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela Piesa
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Piesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PiesaRepository extends JpaRepository<Piesa, Integer> {

    @Query(value = "SELECT * FROM Piesa", nativeQuery = true)
    List<Piesa> toataListaPiese();

    @Query(value = "SELECT * FROM Piesa WHERE Denumire LIKE %?1% OR Producator LIKE %?1%", nativeQuery = true)
    List<Piesa> cautaPiesa(String keyword);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO Piesa (Denumire, Producator, Stoc, Pret) VALUES (?1, ?2, ?3, ?4)", nativeQuery = true)
    void adaugaPiesa(String denumire, String producator, Integer stoc, Double pret);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Piesa SET Denumire=?1, Producator=?2, Stoc=?3, Pret=?4 WHERE PiesaID=?5", nativeQuery = true)
    void modificaPiesa(String denumire, String producator, Integer stoc, Double pret, Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Piesa WHERE PiesaID = ?1", nativeQuery = true)
    void stergePiesa(Integer id);

    @Query(value = "SELECT * FROM Piesa WHERE PiesaID = ?1", nativeQuery = true)
    Piesa gasesteDupaId(Integer id);
}