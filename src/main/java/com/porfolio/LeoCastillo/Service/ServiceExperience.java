package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.Experience;
import com.porfolio.LeoCastillo.Respository.RepositoryExp;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
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
}