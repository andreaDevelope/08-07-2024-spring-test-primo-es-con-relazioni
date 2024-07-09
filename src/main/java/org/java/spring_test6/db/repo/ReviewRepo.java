package org.java.spring_test6.db.repo;

import org.java.spring_test6.db.pojo.Recensioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepo
        extends JpaRepository<Recensioni, Integer> {

}