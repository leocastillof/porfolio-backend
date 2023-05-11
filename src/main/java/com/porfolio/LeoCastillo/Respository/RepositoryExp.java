package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.Experience;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RepositoryExp extends JpaRepository<Experience, Integer>{
    public Optional<Experience> findByNameE(String nameE);
    public boolean existsByNameE(String nameE);
}
