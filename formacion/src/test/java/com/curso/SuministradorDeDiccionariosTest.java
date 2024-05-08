package com.curso;

//import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.function.*;
import org.junit.jupiter.api.*;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS) // Solo se hace 1 instancia de la clase de test.
                                                  // Y esto permitiría que als funciones BeforeAll y AfterAll no sean static.
class SuministradorDeDiccionariosTest {

    /*
    Optional<Diccionario> getDiccionario(String idioma);
     */
    private static SuministradorDeDiccionarios suministrador;

    @BeforeAll // JUNIT ejecuta esta función antes de ejecutar los tests. La función debe ser static.
    static void prepararSuministrador() {
        suministrador = SuministradorDeDiccionariosFactory.getInstance();
    }
    // JUNIT por defecto crea una instancia de la clase de test para cada test que ejecuta.
    // Por eso, los beforeAll y afterAll son static.
    // Ese comportamiento lo puedo cambiar... mediante la anotación @TestInstance

    //@AfterAll.. Equivalente a BeforeAll. Se ejecuta después de los tests. También debe ser static.

    // @BeforeEach y @AfterEach: Se ejecutan antes y después de cada test. Estas funciones no deben ser static.

    @Test
    @DisplayName("Preguntar por un diccionario que sé que existe")
    void testTienesDiccionarioDeExistente() {
        // GIVEN: Partiendo de un suministrador de diccionarios que tiene un diccionario de ES
        String idioma = "ES";
        assertTrue(suministrador.tienesDiccionarioDe(idioma));
    }
    @Test
    @DisplayName("Preguntar por un diccionario que sé que no existe")
    void testTienesDiccionarioDeNoExistente() {
        // GIVEN: Partiendo de un suministrador de diccionarios que tiene un diccionario de ES
        String idioma = "DE LOS ELFOS";
        assertFalse(suministrador.tienesDiccionarioDe(idioma));
    }
    @Test
    @DisplayName("Preguntar por un diccionario null")
    void testTienesDiccionarioDeNull() {
        /*try {
            suministrador.tienesDiccionarioDe(null);
        }catch(NullPointerException e) {
            return;
        }
        fail("Debería haber cascao'");*/
        assertThrows(NullPointerException.class, () -> suministrador.tienesDiccionarioDe(null));
    }

    @Test
    @DisplayName("Recuperar un diccionario que sé que existe")
    void testDameDiccionarioDeExistente() {
        // GIVEN: Partiendo de un suministrador de diccionarios que tiene un diccionario de ES
        String idioma = "ES";
        Optional<Diccionario> diccionario = suministrador.getDiccionario(idioma);
        assertTrue(diccionario.isPresent());
        assertEquals(idioma, diccionario.get().getIdioma()); // IMPORTANTISIMO
    }
    @Test
    @DisplayName("Recuperar un diccionario que sé que existe")
    void testDameOtroDiccionarioDeExistente() {
        // GIVEN: Partiendo de un suministrador de diccionarios que tiene un diccionario de ES
        String idioma = "es";
        assertTrue(suministrador.getDiccionario(idioma).isPresent());
    }
    @Test
    @DisplayName("Recuperar un diccionario que sé que no existe")
    void testDameDiccionarioDeNoExistente() {
        // GIVEN: Partiendo de un suministrador de diccionarios que tiene un diccionario de ES
        String idioma = "DE LOS ELFOS";
        assertFalse(suministrador.getDiccionario(idioma).isPresent());
    }
    @Test
    @DisplayName("Recuperar un diccionario null")
    void testDameDiccionarioNull() {
        // GIVEN: Partiendo de un suministrador de diccionarios que tiene un diccionario de ES
        assertThrows(NullPointerException.class, () -> suministrador.getDiccionario(null));
    }
}
