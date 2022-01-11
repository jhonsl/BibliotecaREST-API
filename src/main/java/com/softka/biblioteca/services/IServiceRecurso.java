package com.softka.biblioteca.services;

import com.softka.biblioteca.dtos.DtoRecurso;

import java.util.Date;
import java.util.List;

public interface IServiceRecurso {

    public DtoRecurso save(DtoRecurso dtoRecurso);
    public DtoRecurso update(DtoRecurso dtoRecurso);
    public DtoRecurso findById(String id);
    public List<DtoRecurso> findRecursosByTematicaOrTipo(String tematica, String tipo);
    public List<DtoRecurso> findAll();
    public List<DtoRecurso> recommend(String id);
    public void delete(String id);
    public boolean isAvailable(String id);
    public void lend(String id, Date fechaPrestamo);
    public void giveBack(String id);
}
