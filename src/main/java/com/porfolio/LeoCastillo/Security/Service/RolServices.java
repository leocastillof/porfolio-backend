package com.porfolio.LeoCastillo.Security.Service;

import com.porfolio.LeoCastillo.Enums.RolName;
import com.porfolio.LeoCastillo.Security.Entity.Rol;
import com.porfolio.LeoCastillo.Security.Repository.iRolRepository;
import jakarta.transaction.Transactional;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolServices {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByName(RolName rolname){
        return irolRepository.findByRolName(rolname);
    }
    
    public void save(Rol rol)
    {
        irolRepository.save(rol);
    }
    
    
}
