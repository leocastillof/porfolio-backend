package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.Person;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Integer> {
    public Optional<Person> findByName(String name);
    public boolean existsByName(String name);
}