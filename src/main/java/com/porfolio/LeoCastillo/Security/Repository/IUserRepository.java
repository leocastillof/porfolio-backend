package com.porfolio.LeoCastillo.Security.Repository;

import com.porfolio.LeoCastillo.Security.Entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Object>{
    Optional<User> findByUsername(String username);
    
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
