package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.Contact;
import com.porfolio.LeoCastillo.Respository.RepositoryContact;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServiceContact {
    @Autowired
    RepositoryContact repositoryContact;
    
    public List<Contact> list(){
        return repositoryContact.findAll();
    }
    
    public Optional<Contact> getOne(int id){
        return repositoryContact.findById(id);
    }
    
    public Optional<Contact> getByDescription(String description){
        return repositoryContact.findByDescription(description);
    }
    
    public void save(Contact aboutMe){
        repositoryContact.save(aboutMe);
    }
    
    public void delete(int id){
        repositoryContact.deleteById(id);
    }
    
    public boolean existsById(int id){
        return repositoryContact.existsById(id);
    }
    
    public boolean existsByDescription(String description){
        return repositoryContact.existsByDescription(description);
    }
}
