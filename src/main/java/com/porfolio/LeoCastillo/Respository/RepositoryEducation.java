package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.Education;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryEducation extends JpaRepository<Education, Integer>{
    public Optional<Education> findByNameE(String name);
    public boolean existsByNameE(String nameE);
}
