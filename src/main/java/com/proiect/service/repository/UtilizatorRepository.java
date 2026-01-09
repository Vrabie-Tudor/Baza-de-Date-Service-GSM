package com.proiect.service.repository;

/** Interfața Repository pentru accesul la datele din tabela Utilizator (Login)
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Utilizator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilizatorRepository extends JpaRepository<Utilizator, Integer> {

    @Query(value = "SELECT * FROM Utilizator WHERE Username = ?1 AND Parola = ?2", nativeQuery = true)
    Utilizator verificaCredidentiale(String username, String parola);
}