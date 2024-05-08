package com.curso.microservicio;

import lombok.Data;

import javax.persistence.*;

@Entity // JEE // J2EE      // Jakarta Enterprise Edition // JDBC, JMS, JPA
@Table(name = "animalitos")
@Data
public class Animalito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String nombre;

    @Column
    private Integer edad;

}
