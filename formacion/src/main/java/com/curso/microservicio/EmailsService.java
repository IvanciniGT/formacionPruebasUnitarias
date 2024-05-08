package com.curso.microservicio;

public interface EmailsService {

    void enviarEmail(String destinatario, String asunto, String cuerpo);

}
