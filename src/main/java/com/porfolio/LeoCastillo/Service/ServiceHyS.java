package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.HyS;
import com.porfolio.LeoCastillo.Respository.RepositoryHyS;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ServiceHyS {
    @Autowired
    RepositoryHyS repositoryHyS;
    
    public List<HyS> list(){
        return repositoryHyS.findAll();
    }
    
    public Optional<HyS> getOne(int id){
        return repositoryHyS.findById(id);
    }
    
    public Optional<HyS> getByName(String name){
        return repositoryHyS.findByName(name);
    }
    
    public void save(HyS skill){
        repositoryHyS.save(skill);
    }
    
    public void delete(int id){
        repositoryHyS.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repositoryHyS.existsById(id);
    }
    
    public boolean existsByName(String name){
        return repositoryHyS.existsByName(name);
    }
}
