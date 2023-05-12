package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Dto.dtoExperience;
import com.porfolio.LeoCastillo.Entity.Experience;
import com.porfolio.LeoCastillo.Security.Controller.Message;
import com.porfolio.LeoCastillo.Service.ServiceExperience;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.StringUtils; 
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = {"https://porfolio-frontend-leo.web.app","http://localhost:4200"})
@RequestMapping("/exp")
public class ExperienceController {
    @Autowired
    ServiceExperience serviceExperience;
    
    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list()
    {
        List<Experience> list = serviceExperience.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experience> getById(@PathVariable("id") int id){
        if(!serviceExperience.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        Experience experience = serviceExperience.getOne(id).get();
        return new ResponseEntity(experience, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoExperience dtoexp)
    {
        if(StringUtils.isBlank(dtoexp.getNameE()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if(serviceExperience.existsByNameE(dtoexp.getNameE()))
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST); 
        
        Experience experience = new Experience(dtoexp.getNameE(), dtoexp.getDescriptionE());
        serviceExperience.save(experience);
        
        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperience dtoexp)
    {
        // Validate exists ID
        if(!serviceExperience.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        // Compare name exp
        if(serviceExperience.existsByNameE(dtoexp.getNameE()) && serviceExperience.getByNameE(dtoexp.getNameE()).get().getId() != id)
            return new ResponseEntity(new Message("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        // It cant be empty
        if(StringUtils.isBlank(dtoexp.getNameE()))
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experience experience = serviceExperience.getOne(id).get();
        experience.setNameE(dtoexp.getNameE());
        experience.setDescriptionE(dtoexp.getDescriptionE());
        
        serviceExperience.save(experience);
        return new ResponseEntity(new Message("Experiencia actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)
    {
        // Validate exists ID
        if(!serviceExperience.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        
         serviceExperience.delete(id);
         
          return new ResponseEntity(new Message("Experiencia eliminada"), HttpStatus.OK);
    }
}
