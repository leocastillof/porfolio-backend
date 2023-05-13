package com.porfolio.LeoCastillo.Respository;

import com.porfolio.LeoCastillo.Entity.Contact;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryContact extends JpaRepository<Contact, Integer>{
    public Optional<Contact> findByDescription(String description);
    public boolean existsByDescription(String description);
}
