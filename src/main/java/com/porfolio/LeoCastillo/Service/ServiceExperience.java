package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.Experience;
import com.porfolio.LeoCastillo.Respository.RepositoryExp;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceExperience {
    @Autowired
    RepositoryExp repositoryExp;
    
    public List<Experience> list()
    {
        return repositoryExp.findAll();
    }
    
    public Optional<Experience> getOne(int id)
    {
        return repositoryExp.findById(id);
    }
    
    public Optional<Experience> getByNameE(String nameE)
    {
        return repositoryExp.findByNameE(nameE);
    }
    
    public void save(Experience exp)
    {
        repositoryExp.save(exp);
    }
    
    public void delete(int id)
    {
        repositoryExp.deleteById(id);
    }
    
    public boolean existsById(int id)
    {
        return repositoryExp.existsById(id);
    }
    
    public boolean existsByNameE(String nameE)
    {
        return repositoryExp.existsByNameE(nameE);
    }
}