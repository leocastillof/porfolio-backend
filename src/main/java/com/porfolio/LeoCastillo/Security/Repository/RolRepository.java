package com.porfolio.LeoCastillo.Security.Repository;

import com.porfolio.LeoCastillo.Enums.RolName;
import com.porfolio.LeoCastillo.Security.Entity.Rol;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
    Optional<Rol> findByRolName(RolName rolName);
}
