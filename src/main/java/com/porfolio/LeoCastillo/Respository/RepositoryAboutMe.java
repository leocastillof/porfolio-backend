package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.AboutMe;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAboutMe  extends JpaRepository<AboutMe, Integer>{
    Optional<AboutMe> findByDescription(String description);
    public boolean existsByDescription(String description);
}