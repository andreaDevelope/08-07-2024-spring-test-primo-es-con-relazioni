package org.java.spring_test6.db.repo;

import org.java.spring_test6.db.pojo.Prodotti;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdottiRepo extends JpaRepository<Prodotti, Integer> {

    @Query("SELECT MIN(p.price) FROM Prodotti p")
    double findMinPrice();

    @Query("SELECT MAX(p.price) FROM Prodotti p")
    double findMaxPrice();

    @Query("SELECT AVG(p.price) FROM Prodotti p")
    double findAvgPrice();

}
