package com.softka.biblioteca;

import com.softka.biblioteca.dtos.DtoRecurso;
import com.softka.biblioteca.models.Recurso;
import com.softka.biblioteca.repositories.RepositoryRecurso;
import com.softka.biblioteca.services.implementation.ServiceRecursoImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
public class ServiceRecursoImpTest {

    @MockBean
    private RepositoryRecurso repositoryRecurso;

    @Autowired
    private ServiceRecursoImp serviceRecursoImp;

    @Test
    @DisplayName("Test find all sucess")
    public void findAll() {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("platero y yo");
        dato1.setAutor("Jhon");
        dato1.setTematica("Aventura");
        dato1.setTipo("cuento");
        dato1.setDisponible(true);
        dato1.setFechaPrestamo(null);

        var dato2 = new Recurso();
        dato1.setId("2222");
        dato1.setTitulo("el principito");
        dato1.setAutor("wilson");
        dato1.setTematica("Aventura");
        dato1.setTipo("cuento");
        dato1.setDisponible(true);
        dato1.setFechaPrestamo(null);

        var list = new ArrayList<Recurso>();
        list.add(dato1);
        list.add(dato2);

        Mockito.when(repositoryRecurso.findAll()).thenReturn(list);

        var result = serviceRecursoImp.findAll();

        Assertions.assertEquals(2, result.size());
        Assertions.assertEquals(dato1.getId(), result.get(0).getId());
        Assertions.assertEquals(dato2.getId(), result.get(1).getId());
    }

    @Test
    @DisplayName("Test save Success")
    public void save() {

        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("platero y yo");
        dato1.setAutor("Jhon");
        dato1.setTematica("Aventura");
        dato1.setTipo("cuento");
        dato1.setDisponible(true);
        dato1.setFechaPrestamo(null);

        var dato2 = new DtoRecurso();
        dato1.setTitulo("platero y yo");
        dato1.setAutor("Jhon");
        dato1.setTematica("Aventura");
        dato1.setTipo("cuento");
        dato1.setDisponible(true);
        dato1.setFechaPrestamo(null);

        Mockito.when(repositoryRecurso.save(any())).thenReturn(dato1);

        var resultado = serviceRecursoImp.save(dato2);

        Assertions.assertNotNull(resultado, "el valor guardado no debe ser nulo");
        Assertions.assertEquals(dato1.getTitulo(), resultado.getTitulo(), "el titulo no corresponde");
        Assertions.assertEquals(dato1.getTematica(), resultado.getTematica(), "la tematica no corresponde");
    }
    
    @Test
    @DisplayName("Test findById")
    public void findById() {
        var dato1 = new Recurso();
        dato1.setId("1111");
        dato1.setTitulo("platero y yo");
        dato1.setAutor("Jhon");
        dato1.setTematica("Aventura");
        dato1.setTipo("cuento");
        dato1.setDisponible(true);
        dato1.setFechaPrestamo(null);

        Mockito.when(repositoryRecurso.findById(dato1.getId())).thenReturn(Optional.of(dato1));

        var resultado = serviceRecursoImp.findById(dato1.getId());

        Assertions.assertEquals(dato1.getId(), resultado.getId());
        Assertions.assertEquals(dato1.getTitulo(), resultado.getTitulo());
        Assertions.assertEquals(dato1.getTipo(), resultado.getTipo());
    }
}
