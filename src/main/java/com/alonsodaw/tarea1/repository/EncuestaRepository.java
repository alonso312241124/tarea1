package com.alonsodaw.tarea1.repository;

import com.alonsodaw.tarea1.entity.Encuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EncuestaRepository extends JpaRepository<Encuesta,Long> {
    List<Encuesta> findByNivelSatisfaccion(String nivelSatisfaccion);
}
