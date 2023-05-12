package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Entity.Person;
import com.porfolio.LeoCastillo.Interface.IPersonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://porfolio-frontend-leo.web.app","http://localhost:4200"})
public class PersonController {
    @Autowired IPersonService ipersonService;
    
    @GetMapping("/person/get")
    public List<Person> getPerson()
    {
        return ipersonService.getPerson();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/person/insert")
    public String createPerson(@RequestBody Person person)
    {
        ipersonService.savePerson(person);
        return "¡La persona fue creada correctamente!";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/person/delete/{id}")
    public String deletePerson(@PathVariable Long id)
    {
        ipersonService.deletePerson(id);
        return "La persona fue eliminada correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/person/edit/{id}")
    public Person editPerson(@PathVariable Long id, 
            @RequestParam("name") String newName,
            @RequestParam("lastname") String newLastName,
            @RequestParam("img") String newImg){
        Person person = ipersonService.findPerson(id);
        
        person.setName(newName);
        person.setLastname(newLastName);
        person.setImg(newImg);
        
        ipersonService.savePerson(person);
        
        return person;
    }
    
    @GetMapping("/person/get/profile")
    public Person findPerson()
    {
        return ipersonService.findPerson((long)1);
    }
}
