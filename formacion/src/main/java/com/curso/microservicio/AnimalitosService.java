package com.curso.microservicio;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Spring monta un patron singlenton para esta clase... y me provee de la instancia
public class AnimalitosService {

    private final AnimalitosRepository miRepo;
    private final EmailsService miEmailsService;

    public AnimalitosService(AnimalitosRepository miRepo, EmailsService miEmailsService) { // Spring
        this.miRepo = miRepo;
        this.miEmailsService = miEmailsService;
    }

    public Animalito nuevoAnimalito(Animalito animalito) {
        // Validar los datos
        if(animalito.getEdad() < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }// TODO Mas validaciones
        // Persistir el animalito en BBDD a través de mi rep
        Animalito animalitoDevuelto = miRepo.save(animalito);
        // Enviar un email a los administradores
        miEmailsService.enviarEmail("correo@correo.com", "Nuevo animalito", "Se ha añadido un nuevo animalito: "+ animalito.getNombre());
        // Devolver el animalito
        return animalitoDevuelto;
    }


    public List<Animalito> recuperarAnimalitos() {
        return miRepo.findAll();
    }

    public Optional<Animalito> recuperarAnimalito(Long id) {
        return miRepo.findById(id);
    }

}
