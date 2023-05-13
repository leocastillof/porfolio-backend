package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Dto.dtoAboutMe;
import com.porfolio.LeoCastillo.Entity.AboutMe;
import com.porfolio.LeoCastillo.Security.Controller.Message;
import com.porfolio.LeoCastillo.Service.ServiceAboutMe;
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
// @CrossOrigin(origins = {"https://porfolio-frontend-leo.web.app","http://localhost:4200"})
@CrossOrigin("*")
@RequestMapping("/aboutme")
public class AboutMeController {
    @Autowired
    ServiceAboutMe serviceAboutMe;
    
    @GetMapping("/list")
    public ResponseEntity<List<AboutMe>> list()
    {
        List<AboutMe> list = serviceAboutMe.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<AboutMe> getById(@PathVariable("id") int id){
        if(!serviceAboutMe.existsById(id))
            return new ResponseEntity(new Message("No existe"), HttpStatus.NOT_FOUND);
        AboutMe aboutMe = serviceAboutMe.getOne(id).get();
        return new ResponseEntity(aboutMe, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAboutMe dtoAbout)
    {
        if(StringUtils.isBlank(dtoAbout.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        AboutMe aboutMe = new AboutMe(dtoAbout.getDescription());
        serviceAboutMe.save(aboutMe);
        
        return new ResponseEntity(new Message("Experiencia agregada"), HttpStatus.OK);
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoAboutMe dtoAbout)
    {
        // Validate exists ID
        if(!serviceAboutMe.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        // Compare name exp
        if(serviceAboutMe.existsByDescription(dtoAbout.getDescription()) && serviceAboutMe.getByDescription(dtoAbout.getDescription()).get().getId() != id)
            return new ResponseEntity(new Message("Esa descripción ya existe"), HttpStatus.BAD_REQUEST);
        // It cant be empty
        if(StringUtils.isBlank(dtoAbout.getDescription()))
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        
        AboutMe aboutme = serviceAboutMe.getOne(id).get();
        aboutme.setDescription(dtoAbout.getDescription());
        
        serviceAboutMe.save(aboutme);
        return new ResponseEntity(new Message("Descripción actualizada"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)
    {
        // Validate exists ID
        if(!serviceAboutMe.existsById(id))
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        
         serviceAboutMe.delete(id);
         
          return new ResponseEntity(new Message("Descripción eliminada"), HttpStatus.OK);
    }
}
