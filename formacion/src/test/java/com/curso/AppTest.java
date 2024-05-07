package com.curso;

//import org.junit.Assert;
//import org.junit.Test;

// Quien crea una instancia de esta clase ... y llama a sus funciones de prueba?
// La máquina virtual de JAVA? NI DE COÑA...
// Para que la JVM ejecute un método de una clase... necesitamos que el método se llame: main y esté declarado como static.

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Este archivo lo ejecuta el framework de testing JUNIT.
// JUnit es quien tiene una clase con un método main que genera una instancia de mi clase:
/*
    public static void main(String[] args) {
        AppTest miAppTest= new AppTest();
        try{
            miAppTest.testSumarDosNumerosPositivos();
            if(no se ha invocado a fail()) {
                System.out.println("SUCCESS");
            } else {
                System.out.println("FAILURE");
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
*/
// Junit no va a ejecutar todas las funciones de mi clase... Solo aquellas que tengan la anotación @Test
class AppTest {
// Cuando le doy a play en el Eclipse o en el IntelliJ, El eclipse o el IntelliJ no ejecutan mi clase AppTest.
// Llaman a una JVM que ejecuta JUNIT... y a JUNIT le pasan la clase AppTest.
// Y esto es lo quiero? YA ESTOY A GUSTO? NO
// Si to do se pone en verde?             NO
// Valen de algo las pruebas que hago en mi equipo? PA' NA !
// Valen pa mi... pa' darme pistillas de como va la cosa.
// Mi equipo puede estar maleao
// (Tener instaladas mierdas que hagan que to0do funcione... Tener configuraciones especiales en Eclipse o en IntelliJ)
// Las pruebas deben pasar en un entorno de pruebas... a ser posible en un entorno limpio y creado desde cero = CONTENEDORES!
// En ese entorno de pruebas... creado como sea, estará instalao' el Eclipse o enl IntelliJ y un tio ahí dando botones? PLAY !!!!
// EVIDENTEMENTE NO...
// Y quién ejecuta las pruebas entonces? MAVEN
// Aunque... MAVEN delega la ejecución de las pruebas en el plugin SUREFIRE
// Y será el surefire el que se lo pida a  JUNIT
    @Test
    void testSumarDosNumerosPositivos() {
        // GIVEN , WHEN , THEN
        // GIVEN: Preparar el entorno
        double numero1 = 5;
        double numero2 = 3;
        // WHEN: Ejecutar lo que quiero probar
        double resultado = App.sumar(numero1, numero2);
        // THEN: Verificar que ha ocurrido TOD0 lo que debería haber ocurrido
        // Las verificaciones habitualmente se hacen con un framework de testing.
        // Si usamos JUNIT, tenemos la clase Assert que nos permite hacer verificaciones.
        // Nos ofrece un montonón de métodos para verificar cosas.
        // No obstante, hoy en día es muy habitual usar otros frameworks de aserciones como AssertJ o Hamcrest.
        // De hecho Hamcrest viene de serie en Spring Boot.
        // En cualquier caso, un método de aserción es un método que en caso que no se cumpla la condición, debe llamar a un método fail().
        // En JUNIT una prueba puede terminar de 3 formas:
        // 1. Correctamente: Si no se lanza ninguna excepción.                                      SUCCESS
        // 2. Incorrectamente: Si se ha invocado al método fail() de la clase Assert de JUNIT.      FAILURE
        // 3. Explosión morrocotonuda: Si se lanza una excepción.                                   ERROR (la prueba ni siquiera acabó)
        double resultadoEsperado = 8;
        double delta = 0;
        /*
            if (Math.abs(resultado-resultadoEsperado) <= delta) {
                // Si no se cumple la condición, se lanza una excepción.
                Assert.fail();
            }
        */              // PRIMERO EL VALOR ESPERADO
        Assertions.assertEquals(resultadoEsperado, resultado, 0);
    }
    @Test
    void testRestarDosNumerosPositivos() {
        double numero1 = 5;
        double numero2 = 3;

        double resultado = App.restar(numero1, numero2);

        double resultadoEsperado = 2;
        Assertions.assertEquals(resultadoEsperado, resultado, 0);
    }

}
