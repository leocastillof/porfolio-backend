package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Dto.dtoAboutMe;
import com.porfolio.LeoCastillo.Entity.AboutMe;
import com.porfolio.LeoCastillo.Security.Controller.Message;
import com.porfolio.LeoCastillo.Service.ServiceAboutMe;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// @CrossOrigin(origins = {"https://porfolio-frontend-leo.web.app","http://localhost:4200"})
@CrossOrigin("*")
@RequestMapping("/aboutme")
public class AboutMeController {
    @Autowired
    ServiceAboutMe serviceAboutMe;
    
    @GetMapping("/list")
    public ResponseEntity<List<AboutMe>> list(){
        List<AboutMe> list = serviceAboutMe.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<AboutMe> getById(@PathVariable("id")int id){
        if(!serviceAboutMe.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        AboutMe aboutMe = serviceAboutMe.getOne(id).get();
        return new ResponseEntity(aboutMe, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!serviceAboutMe.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        serviceAboutMe.delete(id);
        return new ResponseEntity(new Message("Descripción eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoAboutMe dtoaboutme){
        if(StringUtils.isBlank(dtoaboutme.getDescription())){
            return new ResponseEntity(new Message("La Descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(serviceAboutMe.existsByDescription(dtoaboutme.getDescription())){
            return new ResponseEntity(new Message("La Descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        AboutMe aboutMe = new AboutMe(
                dtoaboutme.getDescription()
            );
        serviceAboutMe.save(aboutMe);
        return new ResponseEntity(new Message("Descripción creada"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoAboutMe dtoaboutme){
        if(!serviceAboutMe.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(serviceAboutMe.existsByDescription(dtoaboutme.getDescription()) && serviceAboutMe.getByDescription(dtoaboutme.getDescription()).get().getId() != id){
            return new ResponseEntity(new Message("La Descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoaboutme.getDescription())){
            return new ResponseEntity(new Message("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        AboutMe aboutMe = serviceAboutMe.getOne(id).get();
        
        aboutMe.setDescription(dtoaboutme.getDescription());
        
        serviceAboutMe.save(aboutMe);
        
        return new ResponseEntity(new Message("Description actualizada"), HttpStatus.OK);
    }
}
