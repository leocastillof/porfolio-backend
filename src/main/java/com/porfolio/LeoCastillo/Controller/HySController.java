package com.porfolio.LeoCastillo.Controller;

import com.porfolio.LeoCastillo.Dto.dtoHyS;
import com.porfolio.LeoCastillo.Entity.HyS;
import com.porfolio.LeoCastillo.Security.Controller.Message;
import com.porfolio.LeoCastillo.Service.ServiceHyS;
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
@RequestMapping("/skill")
public class HySController {
    @Autowired
    ServiceHyS serviceHyS;

    @GetMapping("/list")
    public ResponseEntity<List<HyS>> list() {
        List<HyS> list = serviceHyS.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById(@PathVariable("id") int id) {
        if (!serviceHyS.existsById(id)) {
            return new ResponseEntity(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
        HyS HYS = serviceHyS.getOne(id).get();
        return new ResponseEntity(HYS, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        if (!serviceHyS.existsById(id)) {
            return new ResponseEntity(new Message("El id no existe"), HttpStatus.NOT_FOUND);
        }
        serviceHyS.delete(id);
        return new ResponseEntity(new Message("Habilidad eliminado"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyS dtohys) {
        if (StringUtils.isBlank(dtohys.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (serviceHyS.existsByName(dtohys.getName())) {
            return new ResponseEntity(new Message("Esa habilidad ya existe"), HttpStatus.BAD_REQUEST);
        }

        HyS HYS = new HyS(dtohys.getName(), dtohys.getPercentage());
        serviceHyS.save(HYS);

        return new ResponseEntity(new Message("Skill añadido correctamente"), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHyS dtohys) {
        //Validamos si existe el ID
        if (!serviceHyS.existsById(id)) {
            return new ResponseEntity(new Message("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombre de skills
        if (serviceHyS.existsByName(dtohys.getName()) && serviceHyS.getByName(dtohys.getName()).get()
                .getId() != id) {
            return new ResponseEntity(new Message("Esa skill ya existe"), HttpStatus.BAD_REQUEST);
        }
        //No puede estar vacio
        if (StringUtils.isBlank(dtohys.getName())) {
            return new ResponseEntity(new Message("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        HyS hYs = serviceHyS.getOne(id).get();
        hYs.setNombre(dtohys.getName());
        hYs.setPercentage(dtohys.getPercentage());

        serviceHyS.save(hYs);
        return new ResponseEntity(new Message("Habilidad actualizada"), HttpStatus.OK);
    }
}
