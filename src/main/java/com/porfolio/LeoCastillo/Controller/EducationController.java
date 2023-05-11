package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Dto.dtoEducation;
import com.porfolio.LeoCastillo.Entity.Education;
import com.porfolio.LeoCastillo.Security.Controller.Message;
import com.porfolio.LeoCastillo.Service.ServiceEducation;
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
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/education")
public class EducationController {
    @Autowired
    ServiceEducation serviceEducation;
    
    @GetMapping("/list")
    public ResponseEntity<List<Education>> list(){
        List<Education> list = serviceEducation.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Education> getById(@PathVariable("id")int id){
        if(!serviceEducation.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Education education = serviceEducation.getOne(id).get();
        return new ResponseEntity(education, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!serviceEducation.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        serviceEducation.delete(id);
        return new ResponseEntity(new Message("Educación eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoEducation dtoeducation){
        if(StringUtils.isBlank(dtoeducation.getNameE())){
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(serviceEducation.existsByNombreE(dtoeducation.getNameE())){
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Education education = new Education(
                dtoeducation.getNameE(), dtoeducation.getDescriptionE()
            );
        serviceEducation.save(education);
        return new ResponseEntity(new Message("Educación creada"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducation dtoeducation){
        if(!serviceEducation.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(serviceEducation.existsByNombreE(dtoeducation.getNameE()) && serviceEducation.getByNmbreE(dtoeducation.getNameE()).get().getId() != id){
            return new ResponseEntity(new Message("El nombre ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtoeducation.getNameE())){
            return new ResponseEntity(new Message("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Education education = serviceEducation.getOne(id).get();
        
        education.setNameE(dtoeducation.getNameE());
        education.setDescriptionE(dtoeducation.getDescriptionE());
        
        serviceEducation.save(education);
        
        return new ResponseEntity(new Message("Educación actualizada"), HttpStatus.OK);
    }
}
