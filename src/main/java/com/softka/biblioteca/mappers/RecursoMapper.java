package com.softka.biblioteca.mappers;

import com.softka.biblioteca.dtos.DtoRecurso;
import com.softka.biblioteca.models.Recurso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RecursoMapper {

    public Recurso fromDTO(DtoRecurso dto){
        Recurso recurso = new Recurso();
        recurso.setId(dto.getId());
        recurso.setTitulo(dto.getTitulo());
        recurso.setAutor(dto.getAutor());
        recurso.setTipo(dto.getTipo());
        recurso.setTematica(dto.getTematica());
        recurso.setDisponible(dto.isDisponible());
        recurso.setFechaPrestamo(dto.getFechaPrestamo());

        return recurso;
    }

    public DtoRecurso fromCollection(Recurso recurso){
        DtoRecurso dtoRecurso = new DtoRecurso();
        dtoRecurso.setId(recurso.getId());
        dtoRecurso.setTitulo(recurso.getTitulo());
        dtoRecurso.setAutor(recurso.getAutor());
        dtoRecurso.setTipo(recurso.getTipo());
        dtoRecurso.setTematica(recurso.getTematica());
        dtoRecurso.setDisponible(recurso.isDisponible());
        dtoRecurso.setFechaPrestamo(recurso.getFechaPrestamo());

        return dtoRecurso;
    }

    public List<DtoRecurso> fromCollectionList(List<Recurso> collection){
        if(collection == null){
            return null;
        }

        List<DtoRecurso> list = new ArrayList(collection.size());
        Iterator iterator = collection.iterator();

        while (iterator.hasNext()){
            Recurso recurso = (Recurso) iterator.next();
            list.add(fromCollection(recurso));
        }

        return list;
    }
}
