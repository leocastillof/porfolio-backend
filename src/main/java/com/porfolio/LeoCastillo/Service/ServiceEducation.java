package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.Education;
import com.porfolio.LeoCastillo.Respository.RepositoryEducation;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceEducation {
    @Autowired
    RepositoryEducation repositoryEducation;
    
    public List<Education> list(){
        return repositoryEducation.findAll();
    }
    
    public Optional<Education> getOne(int id){
        return repositoryEducation.findById(id);
    }
    
    public Optional<Education> getByNmbreE(String nameE){
        return repositoryEducation.findByNameE(nameE);
    }
    
    public void save(Education education){
        repositoryEducation.save(education);
    }
    
    public void delete(int id){
        repositoryEducation.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repositoryEducation.existsById(id);
    }
    
    public boolean existsByNombreE(String nameE){
        return repositoryEducation.existsByNameE(nameE);
    }
}
