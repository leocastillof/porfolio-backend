package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.AboutMe;
import com.porfolio.LeoCastillo.Respository.RepositoryAboutMe;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceAboutMe {
    @Autowired
    RepositoryAboutMe repositoryAboutMe;
    
    public List<AboutMe> list(){
        return repositoryAboutMe.findAll();
    }
    
    public Optional<AboutMe> getOne(int id){
        return repositoryAboutMe.findById(id);
    }
    
    public Optional<AboutMe> getByDescription(String description){
        return repositoryAboutMe.findByDescription(description);
    }
    
    public void save(AboutMe aboutMe){
        repositoryAboutMe.save(aboutMe);
    }
    
    public void delete(int id){
        repositoryAboutMe.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repositoryAboutMe.existsById(id);
    }
    
    public boolean existsByDescription(String description){
        return repositoryAboutMe.existsByDescription(description);
    }
}
