package com.softka.biblioteca.repositories;

import com.softka.biblioteca.dtos.DtoRecurso;
import com.softka.biblioteca.models.Recurso;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryRecurso extends MongoRepository<Recurso, String> {

    public List<Recurso> findRecursoByTematicaOrTipo(String tipo, String tematica);
}
