package com.curso.microservicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(classes = MicroservicioAppTest.class)
// JUNIT
@ExtendWith(SpringExtension.class)
// Le indica a UNIT que algunos parametros del constructor no los debe proveer el..
// sino que puede a su vez pedirlos a otro
class AnimalitosServiceUnitTest {

    private final AnimalitosService miServicio;

    @MockBean
    // Anotación de Spring...
    // Internamente llama a la anotación @Mock de Mockito
    // La anotación Mock de Mockito crea un dummy de la clase que se le pasa como parámetro
    // Y la anotación MockBean, hace que Spring utilice esa instancia cada vez que alguien pida un objeto de esa clase
    private EmailsService miEmailsServiceMock;

    @Captor
    private ArgumentCaptor<String> destinatarioCaptor;
    @Captor
    private ArgumentCaptor<String> asuntoCaptor;

    @MockBean
    private AnimalitosRepository miRepoMockStub;

    AnimalitosServiceUnitTest(@Autowired AnimalitosService miServicio){
        this.miServicio = miServicio;
    }

    @Test
    void nuevoAnimalito() {
        // GIVEN
        Animalito animalito = new Animalito();
        animalito.setNombre("Pepito");
        animalito.setEdad(3);
        Animalito animalitoQueDevuelveElRepo = new Animalito();
        animalitoQueDevuelveElRepo.setNombre(animalito.getNombre());
        animalitoQueDevuelveElRepo.setEdad(animalito.getEdad());
        animalitoQueDevuelveElRepo.setId(33L);
        Mockito.when(miRepoMockStub.save(animalito)).thenReturn(animalitoQueDevuelveElRepo);
        // WHEN
        Animalito animalitoDevuelto = miServicio.nuevoAnimalito(animalito);
        // THEN
        // Compruebo que el animalito devuelto tiene el mismo nombre que el que le he dado
        Assertions.assertEquals(animalito.getNombre(), animalitoDevuelto.getNombre());
        // Compruebo que el animalito devuelto tiene la misma edad que el que le he dado
        Assertions.assertEquals(animalito.getEdad(), animalitoDevuelto.getEdad());
        // Compruebo que el animalito devuelto tiene un id distinto de null
        Assertions.assertNotNull(animalitoDevuelto.getId());
        // Quiero asegurar que se haya llamado al método save del repositorio
       // Assertions.assertEquals(animalitoQueDevuelveElRepo.getId(), animalitoDevuelto.getId());
        // Quiero asegurar que se haya llamado al método enviarEmails del servicio de emails
        Mockito.verify(miEmailsServiceMock).enviarEmail(destinatarioCaptor.capture(), asuntoCaptor.capture(), Mockito.anyString());
        Assertions.assertEquals("correo@correo.com", destinatarioCaptor.getValue());
        Assertions.assertEquals("Nuevo animalito", asuntoCaptor.getValue());
    }
}