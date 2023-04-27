package com.porfolio.LeoCastillo.Service;

import com.porfolio.LeoCastillo.Entity.Person;
import com.porfolio.LeoCastillo.Interface.IPersonService;
import com.porfolio.LeoCastillo.Respository.IPersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImpPersonService implements IPersonService{
    
    @Autowired IPersonRepository ipersonRepository;

    @Override
    public List<Person> getPerson() {
        List<Person> person = ipersonRepository.findAll();
        return person;
    }

    @Override
    public void savePerson(Person person) {
        ipersonRepository.save(person);
    }

    @Override
    public void deletePerson(Long id) {
        ipersonRepository.deleteById(id);
    }

    @Override
    public Person findPerson(Long id) {
        Person person = ipersonRepository.findById(id).orElse(null);
        return person; 
    }
    
}
