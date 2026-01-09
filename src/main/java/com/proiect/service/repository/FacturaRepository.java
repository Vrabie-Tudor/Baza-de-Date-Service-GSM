package com.proiect.service.repository;

/** Interfața Repository pentru Facturi
 * @author Vrabie Tudor-Ștefan
 * @version 10 Decembrie 2025
 */

import com.proiect.service.entity.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Integer> {

    @Query(value = "SELECT * FROM Factura", nativeQuery = true)
    List<Factura> toataListaFacturi();

    @Query(value = "SELECT F.* FROM Factura F JOIN Client C ON F.ClientID = C.ClientID WHERE C.Nume LIKE %?1% OR C.Prenume LIKE %?1%", nativeQuery = true)
    List<Factura> cautaFactura(String keyword);
}