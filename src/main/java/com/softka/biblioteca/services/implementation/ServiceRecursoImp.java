package com.softka.biblioteca.services.implementation;

import com.softka.biblioteca.BibliotecaApplication;
import com.softka.biblioteca.dtos.DtoRecurso;
import com.softka.biblioteca.mappers.RecursoMapper;
import com.softka.biblioteca.models.Recurso;
import com.softka.biblioteca.repositories.RepositoryRecurso;
import com.softka.biblioteca.services.IServiceRecurso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ServiceRecursoImp implements IServiceRecurso {

    @Autowired
    private RepositoryRecurso repositoryRecurso;
    private RecursoMapper mapper = new RecursoMapper();
    private static Logger log = LoggerFactory.getLogger(BibliotecaApplication.class);

    @Override
    public DtoRecurso save(DtoRecurso dtoRecurso) {
        Recurso recurso = mapper.fromDTO(dtoRecurso);
        return mapper.fromCollection(repositoryRecurso.save(recurso));
    }

    @Override
    public DtoRecurso update(DtoRecurso dtoRecurso) {
        Recurso recurso = mapper.fromDTO(dtoRecurso);
        repositoryRecurso.findById(recurso.getId()).orElseThrow(() -> new RuntimeException("El recurso no existe"));
        return mapper.fromCollection(repositoryRecurso.save(recurso));
    }

    @Override
    public DtoRecurso findById(String id) {
        Recurso recurso = repositoryRecurso.findById(id).orElseThrow(() -> new RuntimeException("El empleado no existe"));
        return mapper.fromCollection(recurso);
    }

    @Override
    public List<DtoRecurso> findRecursosByTematicaOrTipo(String tematica, String tipo) {
        List<Recurso> recursos = repositoryRecurso.findRecursoByTematicaOrTipo(tematica, tipo);

        if(recursos.isEmpty())
            throw new RuntimeException("No hay recursos de esa tematica o tipo");

        return mapper.fromCollectionList(recursos);
    }

    @Override
    public List<DtoRecurso> findAll() {
        List<Recurso> recursos = repositoryRecurso.findAll();

        return mapper.fromCollectionList(recursos);
    }

    @Override
    public List<DtoRecurso> recommend(String id) {
        Recurso recurso = repositoryRecurso.findById(id).orElseThrow(() -> new RuntimeException("El recurso no existe"));
        String tipo = recurso.getTipo();
        String tematica = recurso.getTematica();
        List<Recurso> recursos = repositoryRecurso.findRecursoByTematicaOrTipo(tematica, tipo);

        if(recursos.isEmpty())
            throw new RuntimeException("No hay ningun recurso para recomendar");

        return mapper.fromCollectionList(recursos);
    }

    @Override
    public void delete(String id) {
        repositoryRecurso.deleteById(id);
    }

    @Override
    public boolean isAvailable(String id) {
        Recurso recurso = repositoryRecurso.findById(id).orElseThrow(() -> new RuntimeException("El recurso no existe"));
        if(recurso.isDisponible())
        {
            log.info("El recurso esta disponible");
            return true;
        }
        log.info("El recurso no esta disponible\nFecha de prestamo: {}",recurso.getFechaPrestamo());
        return false;
    }

    @Override
    public void lend(String id, Date fechaPrestamo) {
        if(isAvailable(id))
        {
            Recurso recurso = repositoryRecurso.findById(id).orElseThrow(() -> new RuntimeException("El recurso no existe"));
            recurso.setDisponible(false);
            recurso.setFechaPrestamo(LocalDate.now());
            update(mapper.fromCollection(recurso));
        }
    }

    @Override
    public void giveBack(String id) {
        Recurso recurso = repositoryRecurso.findById(id).orElseThrow(() -> new RuntimeException("El recurso no existe"));
        recurso.setDisponible(true);
        repositoryRecurso.save(recurso);
    }
}
