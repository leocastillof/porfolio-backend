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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("exp")
@CrossOrigin(origins = "https://localhost:4200")
public class ExperienceController {
    @Autowired
    ServiceExperience serviceExperience;
    
    @GetMapping("/list")
    public ResponseEntity<List<Experience>> list()
    {
        List<Experience> list = serviceExperience.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
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
    
}
