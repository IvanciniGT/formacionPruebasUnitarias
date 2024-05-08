# Programación funcional?

Otro paradigma de programación, igual que la programación imperativa o la orientada a objetos.
Cuando el lenguaje me permite que una variable apunte a una función... y posteriormente ejecutar esa función desde la variable... estamos hablando de programación funcional.

Desde el momento que puedo hacer esto, puedo crear funciones que reciban funciones como argumentos. y/o que devuelvan funciones como resultado... Y AQUI EXPLOTA LA CABEZA !!!!

String texto = "HOLA";

Las variables NO son cajitas donde meto cosas... al menos no en JAVA, ni en JS, ni en python... en esos lenguajes las variables son referencias a objetos (PUNTEROS)

    1- Crea en memoria un objeto de tipo TEXTO, con valor "hola";
    2- Crea una variable que puede apuntar a objetos de tipo String
    3- Hace que la variable apunte al objeto creado en el paso 1
        Asignamos la variable al DATO HOLA
    
texto = "ADIOS";
    1- Crea en memoria un objeto de tipo TEXTO, con valor "adios";
        Donde lo crea? En el mismo sitio donde estaba "hola" o en otro??? EN OTRO !
    2- Mueve la variable. LA VARIA. para que ahora apunte al objeto "adios"
    ... El objeto "hola" se quede huérfano... nadie le quiere... nadie le apunta.-. y se convierte en  BASURA: GARBAGE
    ... y quizás en algún momento o no.... entrará el recolector de basura y lo eliminará de la memoria.
           ^^^^ es lo que convierte a JAVA en u lenguaje de programación NO DETERMINISTA, que invalida el uso de JAVA para ciertos proyectos... como por ejemplo, sistemas de tiempo real.

--- 

Una cosa es hacer TEST-FIRST... las pruebas primero...

Otra cosa ss seguir / cumplir con los principios FIRST de diseño de pruebas

Igual que tengo los principios SOLID para el diseño de código, tengo los principios FIRST para el diseño de pruebas.

    F - Fast: Las pruebas deben ser rápidas
    I - Independent: Las pruebas deben ser independientes
    R - Repeatable: Las pruebas deben ser repetibles
    S - Self-validating: Las pruebas deben ser autovalidables: DEBEN COMPROBAR TODAS LAS CONSECUENCIAS DE LA ACCION QUE SE ESTÁ PROBANDO
    T - Timely: Las pruebas deben ser oportunas. Estar diseñadas en el momento adecuado. Si no... para qué sirven? Ya no los hagas!