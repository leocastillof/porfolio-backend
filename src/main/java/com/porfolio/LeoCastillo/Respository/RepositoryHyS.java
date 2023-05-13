package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.HyS;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryHyS extends JpaRepository<HyS, Integer>{
    Optional<HyS> findByName(String name);
    public boolean existsByName(String name);
}
