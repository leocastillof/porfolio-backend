package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Dto.dtoContact;
import com.porfolio.LeoCastillo.Entity.Contact;
import com.porfolio.LeoCastillo.Security.Controller.Message;
import com.porfolio.LeoCastillo.Service.ServiceContact;
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
//@CrossOrigin(origins = {"https://porfolio-frontend-leo.web.app","http://localhost:4200"})
@CrossOrigin("*")
@RequestMapping("/education")
public class ContactController {
    @Autowired
    ServiceContact serviceContact;
    
    @GetMapping("/list")
    public ResponseEntity<List<Contact>> list(){
        List<Contact> list = serviceContact.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/detail/{id}")
    public ResponseEntity<Contact> getById(@PathVariable("id")int id){
        if(!serviceContact.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.BAD_REQUEST);
        }
        
        Contact contact = serviceContact.getOne(id).get();
        return new ResponseEntity(contact, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        if(!serviceContact.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        serviceContact.delete(id);
        return new ResponseEntity(new Message("Información eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoContact dtocontact){
        if(StringUtils.isBlank(dtocontact.getDescription())){
            return new ResponseEntity(new Message("La descripción es obligatoria"), HttpStatus.BAD_REQUEST);
        }
        if(serviceContact.existsByDescription(dtocontact.getDescription())){
            return new ResponseEntity(new Message("La descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        
        Contact contact = new Contact(
                dtocontact.getDescription(), dtocontact.getFacebook(), dtocontact.getTwitter(),
                dtocontact.getGoogle(), dtocontact.getLinkedin(), dtocontact.getInstagram(),
                dtocontact.getWhatsapp()
            );
        serviceContact.save(contact);
        return new ResponseEntity(new Message("Contacto creado"), HttpStatus.OK);
                
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoContact dtocontact){
        if(!serviceContact.existsById(id)){
            return new ResponseEntity(new Message("No existe el ID"), HttpStatus.NOT_FOUND);
        }
        if(serviceContact.existsByDescription(dtocontact.getDescription()) && serviceContact.getByDescription(dtocontact.getDescription()).get().getId() != id){
            return new ResponseEntity(new Message("La Descripción ya existe"), HttpStatus.BAD_REQUEST);
        }
        if(StringUtils.isBlank(dtocontact.getDescription())){
            return new ResponseEntity(new Message("El campo no puede estar vacio"), HttpStatus.BAD_REQUEST);
        }
        
        Contact contact = serviceContact.getOne(id).get();
       
        contact.setDescription(dtocontact.getDescription());
        contact.setFacebook(dtocontact.getFacebook());
        contact.setTwitter(dtocontact.getTwitter());
        contact.setGoogle(dtocontact.getGoogle());
        contact.setLinkedin(dtocontact.getLinkedin());
        contact.setInstagram(dtocontact.getInstagram());
        contact.setWhatsapp(dtocontact.getWhatsapp());
        
        
        serviceContact.save(contact);
        
        return new ResponseEntity(new Message("Contacto actualizado"), HttpStatus.OK);
    }
}
