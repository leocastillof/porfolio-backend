package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    
}