package com.curso;

import io.cucumber.java.en.Given;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import org.junit.jupiter.api.Assertions;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
// Le indico a JUNIT que esta clase contiene una suite de pruebas.
@IncludeEngines("cucumber")
// Le indico a JUNIT las pruebas de esta suite de pruebas las debe ejecutar CUCUMBER
                // "cucumber" es el nombre de un plugin que permite integrar CUCUMBER con JUNIT PLATFORM ENGINE
                // Esa palabrita me la da la dependencia: cucumber-junit-platform-engine
@SelectClasspathResource("features/diccionario.feature")
// Pasale al engine "cucumber" el archivo de features que contiene las pruebas.
public class DiccionarioCucumberStepsTest {

    private Diccionario diccionario;
    private boolean respuesta;
    @Given("que tengo un diccionario de {string}")
    public void que_tengo_un_diccionario_de(String idioma) {
        diccionario = SuministradorDeDiccionariosFactory.getInstance().
                getDiccionario(idioma).get();
    }

    @Cuando("le pregunto por la palabra {string}")
    public void le_pregunto_por_la_palabra(String palabra) {
        respuesta = diccionario.existe(palabra);
    }

    @Entonces("me contesta que {string} la tiene.")
    public void me_contesta_que_la_tiene(String respuesta) {
        Assertions.assertEquals(respuesta.equalsIgnoreCase("si"),this.respuesta);
    }
}
