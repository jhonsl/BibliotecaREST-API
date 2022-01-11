package com.softka.biblioteca.controllers;

import com.softka.biblioteca.BibliotecaApplication;
import com.softka.biblioteca.dtos.DtoRecurso;
import com.softka.biblioteca.services.implementation.ServiceRecursoImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recursos")
public class RecursoController {

    @Autowired
    ServiceRecursoImp serviceRecursoImp;

    private static Logger log = LoggerFactory.getLogger(BibliotecaApplication.class);

    @GetMapping("/{id}")
    public ResponseEntity<DtoRecurso> findById(@PathVariable("id") String id) {
        return new ResponseEntity(serviceRecursoImp.findById(id), HttpStatus.OK);
    }
    @GetMapping()public ResponseEntity<List<DtoRecurso>> findAll() {
        return new ResponseEntity(serviceRecursoImp.findAll(), HttpStatus.OK);
    }

    @GetMapping("/recommendations/{id}")
    public ResponseEntity<List<DtoRecurso>> recommend(@PathVariable("id") String id) {
        return new ResponseEntity(serviceRecursoImp.recommend(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<DtoRecurso> create(@RequestBody DtoRecurso dtoRecurso) {
        return new ResponseEntity(serviceRecursoImp.save(dtoRecurso), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DtoRecurso> update(@RequestBody DtoRecurso dtoRecurso) {
        if(dtoRecurso.getId() != null)
            return new ResponseEntity(serviceRecursoImp.update(dtoRecurso), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        try {
            serviceRecursoImp.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
