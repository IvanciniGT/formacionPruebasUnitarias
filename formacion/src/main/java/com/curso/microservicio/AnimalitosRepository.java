package com.curso.microservicio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnimalitosRepository extends JpaRepository<Animalito, Long> {
    List<Animalito> findByEdadGreaterThan(Integer edadMinima);
}
