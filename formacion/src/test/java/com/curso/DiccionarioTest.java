package com.curso;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DiccionarioTest {
    /*
    boolean existe(String termino);
    Optional<List<String>> getDefiniciones(String termino);
    */

    private static Diccionario miDiccionario;

    @BeforeAll
    static void cargarDiccionario() {
        miDiccionario = SuministradorDeDiccionariosFactory.getInstance().getDiccionario("es").get();
    }

    @ParameterizedTest
    @DisplayName("Preguntar por una palabra que existe")
    // Los test parametrizados requieren un SOURCE, que se encarga de proporcionar los valores
    // Dentro de la librería de test parametrizados de JUnit 5, haymuchos tipos de sources
    // Que se configuran con distintas anotaciones: ValueSource, EnumSource, MethodSource, CsvSource, CsvFileSource, ArgumentsSource
    @ValueSource(strings ={"pera", "PERA", "Pera", "MANZANA", "MELÓN"})
    void testExiste(String palabra) {
        assertTrue(miDiccionario.existe(palabra));
    }
    @ParameterizedTest
    @DisplayName("Preguntar por una palabra que existe")
    @ValueSource(strings ={"federico", "Enriqueta", "Pringao el que lo lea"})
    void testNoExiste(String palabra) {
        assertFalse(miDiccionario.existe(palabra));
    }

    @ParameterizedTest
    @DisplayName("Definiciones de una palabra que existe")
    @CsvSource({
            "pera,1,fruto del peral",
            "MANZANA,1,fruto del manzano",
            "Melón,2,fruto del melonero"
    })
    void testDefinicionesExistentes(String palabra, int cantidadDeDefiniciones, String primeraDefinicion) {
        Optional<List<String>> posiblesDefiniciones = miDiccionario.getDefiniciones(palabra);
        assertTrue(posiblesDefiniciones.isPresent());
        List<String> definiciones = posiblesDefiniciones.get();
        assertTrue(definiciones.size() >= cantidadDeDefiniciones);
        assertTrue(definiciones.get(0).equalsIgnoreCase(primeraDefinicion));
    }

    @ParameterizedTest
    @DisplayName("Definiciones de una palabra que existe")
    @CsvFileSource( resources = "/definiciones.csv", numLinesToSkip = 1)
    void testDefinicionesExistentes2(String palabra, int cantidadDeDefiniciones, String primeraDefinicion) {
        Optional<List<String>> posiblesDefiniciones = miDiccionario.getDefiniciones(palabra);
        assertTrue(posiblesDefiniciones.isPresent());
        List<String> definiciones = posiblesDefiniciones.get();
        assertTrue(definiciones.size() >= cantidadDeDefiniciones);
        assertTrue(definiciones.get(0).equalsIgnoreCase(primeraDefinicion));
    }
    // 10 funciones más y acabao!
}
