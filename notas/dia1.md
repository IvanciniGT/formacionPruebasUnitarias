
# Pruebas unitarias - Junit

JUnit es un framework JAVA para el desarrollo de pruebas automatizadas.

---

# Testing

## Vocabulario en el mundo del testing

- ERROR     Los humanos cometemos errores (errar es humano). Cometemos esos errores por:
              - estar cansado
              - distraídos
              - falta de conocimiento
- DEFECTO   Al cometer un error (un humano) puede introducir un DEFECTO en un producto.
- FALLO     Ese defecto puede (o no) manifestarse en el futuro como un FALLO, al usar el producto.

## Para qué sirven las pruebas?

- Asegurar el cumplimiento de unos requisitos.
  ~~Asegurarnos que el código (x - funciona) correctamente~~
- Intentar identificar la mayor cantidad posible de defectos en el producto antes de su paso a producción (su uso por el cliente
  final) : REVISION !
  - En ocasiones realizaremos pruebas que nos ayuden con la identificación DIRECTA de defectos. (ESTO ES MENOS HABITUAL que...)
- Intentar generar la mayor cantidad posible de FALLOS en el producto antes de su paso a producción, para desde ellos:
  - Identificar y subsanar los DEFECTOS que los han generado: DEPURACIÓN / DEBUGGING
- Facilitar el debugging... Es decir, una vez identificado un fallo, contar con la mayor cantidad posible de información para la rápida identificación del defecto que lo ha generado. (2)
- Puedo hacer un análisis de causas raíz de los defectos detectados, para tomar acciones preventivas que eviten nuevos ERRORES > DEFECTOS > FALLOS en el futuro.
- Extraer conocimiento que aplicar en futuros proyectos.
- Saber qué tal va el proyecto! (1)
- Muchas más cosas...

## Tipos de pruebas

Hay muchas formas paralelas entre si de clasificar las pruebas. Algunas de las más comunes son:
Toda prueba, se debe centrar en una única característica del sistema o componente del sistema. Por qué? (2)

### En base al objeto de prueba.

- Pruebas funcionales:        Se centrar en requisitos funcionales del proyecto
- Pruebas no funcionales:     Se centran otro tipo de requisitos del proyecto
  - De Carga
  - Seguridad
  - De rendimiento
  - De estrés
  - De UX

### En base al nivel de la prueba (el contexto en el que hago la prueba: SCOPE)

- Unitarias                 Se centran en un componente AISLADO del sistema

     TREN: Soy un fabricante de trenes. Mi labor principalmente es de diseño del sistema / componentes e integración.
        - Motor                     Me llega el motor... que hago lo primero? Lo probaré!
                                      Monto un bastidor (4 hierros mal-soldaos) pa' sujetar el motor... lo justito pa' meterle corriente y a ver si gira.
                                      Ese bastidor (test-double) me sirve para algo luego? Va a ser parte del tren? NO
                                      Pero he de montarlo... para poder hacer la prueba.
        - Ruedas
        - Asientos                  Pruebas a mi asiento:
                                    - Carga.......... A ver cuanto aguanta
                                    - De estrés...... En 1 año sienta que te sienta... tios y tias... se desgasta mucho o qué?
                                    - De seguridad... En una curva sujeta al tio/a o se me va a la mierda?
                                    - De UX.......... Se le queda al tio/a el culo cuadrado/a?
                                    Para probarlo, montaré una plataforma, con una plancha de acero y 4 tubos mal soldaso... donde sujetar el asiento... y quizás la pongo sobre una máquina que le dé 4 meneos pa' los laos. 
        - Sistema de frenos         Me llega el sistema de frenos... Lo pruebo? o me arriesgo a montarlo sin probar?
                                        Lo pruebo... monto un bastidor (4 hierros mal soldaos), lo justito pa' meterle corriente 
                                        (ejecuto una función F1) y a ver si cierra las pinzas.
                                        A lo mejor monto un sensor de presión... en las pinzas... para ver si la presión es la adecuada.
                                        Por cierto... ese sensor o el bastidor? valen luego para algo? NO... pero... los necesito para hacer la prueba.
        - Chasis

        Me garantiza el haber realizado todas estas pruebas unitarias que el tren funcionará en el futuro? NO
            Pero qué consigo? CONFIANZA +1 = VAMOS BIEN !
        Los pasos que doy, son en firme.. no he de dar marcha atrás luego.. y des-soldar el motor del chasis... que cuesta una pasta!

- De integración            En la COMUNICACIÓN entre 2 o más componentes del sistema
        - Sistema de frenos + Rueda     Monto un bastidor en el que instalo el sistema de frenos... la rueda, con su disco de freno 
                                        en medio de las pinzas... y meto corriente (ejecuto una función F1)... a ver si la rueda se para.
                                        Pues mira tu que no!... la rueda sigue girando... y las pinzas se cierran.
                                        Pero resulta que las pinzas no cierran lo suficiente para el grosor de disco de freno que estoy usando = CAGADA !
                                        El sistema de frenos no es capaz de comunicar la ENERGIA DE ROZAMIENTO al disco de freno.
                                        Menos mal que lo he visto... si no me tocaría luego desmontar el tren entero para meter discos de freno más anchos... que a lo mejor no entran en el chasis... y hay que rediseñar el chasis.

        Me garantiza el haber realizado todas estas pruebas de integración que el tren funcionará en el futuro? NO
            Pero qué consigo? CONFIANZA +1 = VAMOS BIEN !
        Los pasos que doy, son en firme.. 
         
- Sistema (end2end)         Se centran en el comportamiento del sistema en su conjunto.
                                    YA TENGO TREN... le pongo en la vía, le doy al play...
                                        Y COÑO, que va pa' trás... y no pa'lante! = CAGADA
                                    El tren no se comporta como debería... y no es por un componente en concreto... es por la interacción de todos ellos.
        Y si hubiera hecho todas las pruebas de sistema y pasan...
            Necesitaría haber hecho las pruebas unitarias y las de integración? YA PA QUÉ! si ya se que el tren en su conjunto (mi producto) funciona... será que todos sus componentes funciona bien y se integran bien entre si.
            Ahora no aportan nada!
        Pregunta... entonces... para que narices hemos hecho las pruebas unitarias y de integración? Si al final con las de sistema me sirve?
            Y si hubiera hecho todas las pruebas de sistema y pasan...
            - Y si no pasan? qué falla? NPI.. ponte a buscar.. y desmontar... y arreglar.. ya con todo el tren montado!
                        Que no te toque rediseñar el chasis, que las ruedas nuevas no entren en el chasis...
            - Y cuándo puedo hacer estas pruebas?  Cuando tenga el sistema completo (el último día) Y antes?
              - Cómo se si voy bien?
              - Cómo se cómo va el proyecto? NPI... así nos pasaba con las metodologías tradicionales (en cascada)

  - Aceptación              Son un subconjunto de las pruebas de sistema

### En base al conocimiento previo del sistema que voy a probar

- Caja blanca               Conozco el código por dentro:    TESTER
- Caja negra                No conozco el código por dentro: DESARROLLADOR : MAL !!!!!

### En base al procedimiento de la prueba

- Dinámicas                 Las que requieren ejecutar el código (poner el sistema en funcionamiento):  FALLOS
- Estáticas                 Las que no requieren ejecutar el código (revisar el código):                DEFECTOS
                                Antiguamente eran las que hacía un desarrollador Senior 
                                Hoy en día las hace una herramienta tipo SONARQUBE, LINTERs


> Ejemplo: R1: El sistema debe ofrecer a los usuarios unos tiempos de respuesta aceptables. ESTO NO VALE TIO !!!! Es totalmente AMBIGUO.
> Ejemplo: R1(versión 2): El sistema debe tardar menos de 200ms en realizar TAL operación.  IGUAL DE MIERDA !!!!!
> Ejemplo: R1(versión 3): El sistema, instalado en TAL entorno, sometido a TAL carga de trabajo, debe ofrecer un percentil 95 de tiempo de respuesta para una determinada tarea inferior a 200ms.  ESTO YA ES UNA BUENA R1 !!!! -> PRUEBA: Prueba de rendimiento, de sistema.
    De hecho los requisitos es lo primero que hoy en día SE PRUEBA!... Me refiero a la definición del requisito: 
        El tester es la primera persona que hoy en día DEBE entrar en un proyecto, para validar los requisitos: PRUEBAS ESTÁTICAS.
    Voy a probar la latencia de red... en ir a la BBDD: 70ms... PRUEBA: Prueba de rendimiento, de unitaria.
        Por cierto... a la vista de este resultado... que tal vamos para el R1(versión 3)?
            Como el puto culo... Que no tenga que ir 2 veces a la BBDD... que entonces ya acumulo: 140ms... y me quedan 60 para:
            - queries + lógica de mi aplicación
            - ESTO CONDICIONA TOTALMENTE EL DISEÑO DEL SISTEMA: SISTEMA CACHE, para no tener que ir mucho a la BBDD...
              y quizás operaciones de actualización ASINCRONAS!


El problema no va ser sólo el saber qué es una prueba unitaria... y por qué he de hacerla!
El problema es que para hacer una prueba unitaria necesito un componente AISLADO... y en nuestros proyectos de software... TENEMOS UNA CANTIDAD DE DEPENDENCIAS ENTRE COMPONENTES ENORME!.. y va a costar un huevo AISLAR COMPONENTES! 
    Aquí nos ayudan:
        - Los test-doubles: MOCKS, FAKES, STUBS, SPYS, DUMMIES
        - Un buen diseño del sistema: En ciertos tipos de diseño, es IMPOSIBLE hacer pruebas unitarias... por definición.
---

# SOLID

Son 5 principios que si los respeto consigo hacer un software fácilmente MANTENIBLE, EVOLUCIONABLE y TESTEABLE.
Como no los siga... el software que voy a montar va a ser un desastrillo... y no voy a poder hacer pruebas unitarias... lo más probable (90% de los casos).

S - Single Responsability Principle         Cada componente debe tener una única responsabilidad... 
                                            y por ende un único motivo para cambiar.
O - Open/Closed Principle                   Un componente debe estar ABIERTO para su extensión... pero CERRADO para su modificación.
                                                ABIERTO: que pueda ser extendido
                                                CLOSED:  que esté listo para ser utilizado sin cambios adicionales.
L - Liskov Substitution Principle           Si un componente espera un tipo de dato... y le paso un subtipo de ese tipo de dato... 
                                            el componente no debe fallar.
I - Interface Segregation Principle         Mejor tener muchas interfaces específicas, que una de propósito general.
D - Dependency Inversion Principle          Los componentes de alto nivel no deben depender de una implementación de un componente de 
                                            bajo nivel... sino de que ambos deben depende de abstracciones (interfaces).
                                            ** ESTE ES CLAVE para poder hacer pruebas unitarias **
---

### Patrón de inyección de dependencias

Patrón por el cual una clase no crea instancias de los objetos que necesita, sino que se le son suministrados desde el exterior.
Me ayuda a cumplir el principio de inversión de dependencias.
---

# Ejemplo de proyecto: 

Quiero un comando que me permita por consola suministrar una palabra y un idioma... Y me devuelva si existe la palabra en ese idioma .... y cuales son sus significados.

## Cuantos proyectos monto para este sistema (repos de git / archivos pom.xml)? 3

    app.jar > diccionarios-api.jar < diccionarios-ficheros.jar
        |                                 ^
        +---------------------------------+


### Proyecto 1 - Frontal: Responsabilidad: Comunicación con el usuario
¿al frontal le importa el backend que provea los diccionarios? NO

    package com.diccionarios.frontal;
    import com.diccionarios.api.SuministradorDeDiccionario;
    import com.diccionarios.api.Diccionario;
    //import com.diccionarios.ficheros.SuministradorDeDiccionariosDesdeFicheros;      // Y ME ACABO DE CAER CON TO'L EQUIPO
                                                                                    // Esa linea acaba de destrozar el proyecto
            // ME ACABO DE CAGAR EN EL PRINCIPIO DE INVERSIÓN DE DEPENDENCIAS
            // He generado una dependencia contra una implementación concreta de un componente de bajo nivel = RUINA ... ENORME !!!!
    import java.util.List;

    public class App {
        public static void main(String[] args) {
            // hace lo que sea y llamaré a procesarPetición. ESTO ESTA GUAY DEL PARAGUAY !!!!
        }

        public void procesarPeticion(String idioma, String palabra, SuministradorDeDiccionario miSuministrador) { // PATRON DE INYECCION
                                                                                                                  // DE DEPENDECIAS
            // SuministradorDeDiccionario miSuministrador = new SuministradorDeDiccionariosDesdeFicheros();
            if(miSuministrador.tienesUnDiccionarioDe(idioma)) {
                Diccionario miDiccionario = miSuministrador.getDiccionario(idioma);
                if(miDiccionario.existe(palabra)) {
                    List<String> definiciones = miDiccionario.getDefiniciones(palabra);
                    for(String definicion : definiciones) {
                        System.out.println(definicion);
                    }
                } else {
                    System.out.println("La palabra no existe en el diccionario");
                }
            } else {
                System.out.println("No tengo un diccionario de ese idioma");
            }
        }
    
    }

    // PREGUNTA! Podría hacer una prueba unitaria de la función procesarPeticion? NI DE COÑA... IMPOSIBLE !
        - Mi componente depende del SuminisradorDeDiccionariosDesdeFicheros... qlo que implica que:
          1. Hasta qye ese componente no esté acabado, no puedo probar el frontal.
          2. Si ya pongo ese... ya tengo a mi componente aislado? NO... porque depende de un componente de bajo nivel concreto.
             YA LE HE PUESTO LA RUEDA AL SISTEMA DE FRENOS. 
          3. Si la prueba falla, que ha fallao? El código de App o el de SuminisradorDeDiccionariosDesdeFicheros? NPI
             Ponte a hacer debugging... y a desmontar el tren entero... para ver que falla. 

    // OTRA PREGUNTA! Y ahora puedo hacerle pruebas unitarias? SI... pero... necesito montar 4 hierros mal soldaos y un sensor de presión: Un STUB (uno de los tipos de testDouble)

    public class SuministradorDeDiccionariosStub implements SuministradorDeDiccionarios {
        public boolean tienesUnDiccionarioDe(String idioma) {
            return true;
        }
        public Diccionario getDiccionario(String idioma) {
            return new Diccionario() {
                public boolean existe(String palabra) {
                    return true;
                }
                public List<String> getDefiniciones(String palabra) {
                    return List.of("Definición 1", "Definición 2");
                }
            };
        }
    }

    Al hacer la prueba unitaria, no le paso una instancia de un suministrador de diccionarios real : FICHEROS, BBDD, SERVICIO RAE... sino un STUB.
    Esta clase puede fallar? NO... si no tiene lógica
    Por lo tanto, si falla algo en la prueba, sé que es el código de App el que falla.
        > Prueba 1: Dado un suministrador que tiene diccionario de idioma ESPAÑOL, y la palabra "CASA", espero que se muestren las definiciones de la palabra "CASA" que son: "Definición 1", "Definición 2"
    Necesito ya tener el código de SuministradorDeDiccionariosDesdeFicheros acabado? NO
        > Prueba 2: Dado un suministrador que no tiene diccionario de Español......
          Funcionaría? NO... necesito el qué? OTRO STUB

    public class SuministradorDeDiccionariosStubQueNoTieneDiccionario implements SuministradorDeDiccionarios {
        public boolean tienesUnDiccionarioDe(String idioma) {
            return false;
        }
        public Diccionario getDiccionario(String idioma) {
            return null; <<< EL SONAR ME ESCUPE ESTE CODIGO A LA CARA !!!!
        }
    }


    // Cuántos caminos puede tomar internamente la función procesarPeticion? 3 = COMPLEJIDAD CICLOMÁTICA
        // No tengo diccionario de ese idioma
        // Si tengo diccionario de ese idioma... pero no tengo la palabra
        // Si tengo diccionario de ese idioma... y tengo la palabra
    // Al menos necesito hacer 3 pruebas unitarias para esta función.
    Y esto implica... que para hacer esas tres pruebas unitarias, necesito cuántos STUB? 3... en serio??? VAYA COÑAZO !!!!
    Bueno.. no pasa nada.. Creemos un FAKE... que es como un STUB.. pero con algo de lógica dentro:

    public class SuministradorDeDiccionariosFake implements SuministradorDeDiccionarios {
        public boolean tienesUnDiccionarioDe(String idioma) {
            return idioma.equals("ESPAÑOL");
        }
        public Diccionario getDiccionario(String idioma) {
            return new Diccionario() {
                public boolean existe(String palabra) {
                    return palabra.equals("CASA");
                }
                public Optional<List<String>> getDefiniciones(String palabra) {
                    return palabra.equalsIgnoreCase("CASA") ? Optional.of(List.of("Definición 1", "Definición 2")): Optional.empty();
                }
            };
        }
    }

    Y este FAKE me sirve para hacer las 3 pruebas unitarias de la función procesarPeticion.
    Un fake ... llevado al extremo se convertiría en LA IMPLEMENTACION REAL !

    Aún así... me parece un coñazo Iván... tener que montar estas clases de mierda... para hacer pruebas unitarias.
    Ya... es lo que hay!
    Bueno.. o no... Siempre nos quedara MOCKITO!
    En ocasiones me interesará crear yo mis STUBS, FAKES, SPYS, MOCK, DUMMIES... pero en ocasiones me interesará que lo haga una herramienta por mi, tipo Mockito.

### Proyecto 2 - Backend: Responsabilidad: Gestionar Diccionarios, palabras.. definiciones

#### Backend con los diccionarios en ficheros de texto

    package com.diccionarios.ficheros;
    import com.diccionarios.api.Diccionario;                    // Esto generando una dependencia entre proyectos 
    import com.diccionarios.api.SuministradorDeDiccionarios;    // 
    import java.util.List;

    public class DiccionarioDesdeFicheros implements Diccionario {
        public boolean existe(String palabra) {
            // Buscar en los ficheros de texto
        }
        public List<String> getDefiniciones(String palabra) {
            // Buscar en los ficheros de texto
        }
    }

    public class SuministradorDeDiccionariosDesdeFicheros implements SuministradorDeDiccionarios {
        public boolean tienesUnDiccionarioDe(String idioma) {
            // Buscar en los ficheros de texto
        }
        public Diccionario getDiccionario(String idioma) {
            // Buscar en los ficheros de texto
        }
    }

    El proyecto diccionarios-ficheros.jar depende del proyecto diccionarios-api.jar.
    Y así se reflejará en su pom.xml
        <dependency>
            <groupId>com.diccionarios</groupId>
            <artifactId>diccionarios-api</artifactId>
            <version>1.0</version>
        </dependency>

#### Backend con los diccionarios en una BBDD

#### Backend que consulta las palabras en un servicio de la RAE.

### Proyecto 3 - API de comunicación entre ambos. ---> diccionarios-api.jar

    package com.diccionarios.api;

    public interface Diccionario {
        public boolean existe(String palabra);
        public List<String> getDefiniciones(String palabra) ; // ESTA FUNCION ES UN DESASTRE. 
            // NO HAY NADIE EN EL CURSO QUE ME PUEDA EXPLICAR EL COMPORTAMIENTO DE ESTA FUNCION
            // Si le paso al diccionario (que es de Español) la palabra "manzana" que me devuelve? Una lista con sus definiciones
            // Y si le paso la palabra archilococo? NPI!!!
                // null                 \
                // una lista vacía      / Son ambiguos... No se que me devuelve la función mirando su firma (signatura):
                    // A mirar código por dentro... si es que lo tengo  \
                    // A leer docu... si es que se han dignao           / En serio? Año 2024... Tengo que hacer lo uno o lo otro?
                // throws NoSuchWordException - No es ambugua... pero es un disparate.
                    // Las excepciones se deben usar para casos que sé que pueden fallar... y no hay forma de anticiparlo.
                    // Las excepciones son caras de generar (en términos computacionales)... Lo primero que hay que hacer es generar un volcar de la pila de ejecución de hilos: Thread.dumpStack()
            // La tendencia habitual preJAVA 1.8 ha sido null
            // En JAVA 1.8 se crea la clase Optional... que es una especie de caja de un objeto... que puede estar llena o no.
            Y QUE QUITA TODA AMBIGUEDAD... ya que por convenio, desde JAVA 1.8 está FATAL ( NO SE HACE) que una función devuelva null en NINGÚN CASO.
        public Optional<List<String>> getDefiniciones(String palabra);
            Optional<List<String>> posiblesDefiniciones = miDiccionario.getDefiniciones("manzana");
            if(posiblesDefiniciones.isPresent()){ // .isEmpty()
                List<String> definiciones = posiblesDefiniciones.get(); // Si hago un get de un optional vacío... me salta una excepción: NoSuchElementException
            }

    }

    public interface SuministradorDeDiccionarios { 
        public boolean tienesUnDiccionarioDe(String idioma);
        public Optional<Diccionario> getDiccionario(String idioma);

    }





---

# Metodologías ágiles:

    HITO 1: Fecha + *Listado de requisitos*
        10 de Junio, tiene que estar el R1, R2, R7
        Qué pasa si el día 10 nno está acabado el R7 -> SE RETRASA EL HITO (VOY CON RETRASO EN EL PROYECTO)
        Lo importante era los requisitos.. esos no los cambiaba.

    ITERACIÓN (SPRINT) 1: Fecha + Listado de requisitos:
        10 de Junio, tiene que estar el R1, R2, R7
        Qué pasa si el día 10 no está acabado el R7 -> La fecha no se toca ni de coña. Y el día 10 -> Paso a producción.
        Y el R7.. pa'l siguiente sprint.
        10% de la funcionalidad     => ESTO IMPLICA PRUEBAS (a nivel de producción)
                                        pruebo el 10%
    ITERACIÓN (SPRINTS) 2: A las 2 semanas
        + 5% de la funcionalidad    => ESTO IMPLICA PRUEBAS (a nivel de producción)
                                        pruebo el +5% y el 10% de antes 
    ITERACIÓN (SPRINTS) 3: A las 2 semanas
        + 15% de la funcionalidad   => ESTO IMPLICA PRUEBAS (a nivel de producción)
                                        pruebo el +15% y el +5% y el 10% de antes

    DE DONDE COJONES SACO LA PASTA Y LOS RECURSOS PA'ESTAR HACIENDO TANTA PRUEBA? De ningún lao.. no hay pasta.. ni recursos.. ni tiempo.
    SOLUCIÓN: AUTOMATIZAR las pruebas!

## Cuál es la característica principal de una metodología ágil?

Entregar el producto de forma INCREMENTAL a mi cliente final (SPRINTs), al contrario de las metodologías tradicionales(en cascada) que entregan el producto final al final del proyecto.

- El software funcionando es la MEDIDA principal de progreso. -> Esta frase define un INDICADOR PARA UN CUADRO DE MANDO.

    La medida principal de progreso es el "software funcionando"
    Cómo mido el grado de avance de mi proyecto? Mediante el concepto "software funcionando".
    Cómo sé qué tal vamos? CRITICO

        "Software funcionando" : Software que funciona.. que hace lo que tiene que hacer.
        ¿Y quién dice que funciona?
            - El desarrollador? Era quien lo hacía en la metodologías tradicionales.
            - El **cliente**?   Ni de coña... Al cliente SIEMPRE le tiene que llegar un producto que funcione.
            - LAS PRUEBAS! Cuántas pruebas se han superado esta semana? 10 de 30 vamos mal!
                                                                        29 de 30 vamos bien!

# DEV-->OPS

Es una cultura, un movimiento, una filosofía en pro de la automatización: de todo lo que está entre el DEV ---> OPS

Entre otras cosas, las pruebas.

INTEGRACION CONTINUA: Tener CONTINUAMENTE el último código de los desarrolladores desplegado en el entorno de INTEGRACIÓN
sometido a pruebas automatizadas... PARA QUE? Cuál es el producto? INFORME DE PRUEBAS ACTUALIZADO EN TIEMPO REAL
Para qué sirve eso?
- Entre otros para (1) saber cómo va mi proyecto.... WIKIPEDIA: DevOps es una práctica complementaria al desarrollo de software ágil
---


# MAVEN

Es una herramienta de automatización de tareas de proyectos principalmente en JAVA:
- Compilación
- Ejecución de pruebas
- Empaquetado
- Envío de mi proyecto a un SonarQube
- La generación de una documentación
- La generación de una imagen de contenedor
  Para algunas de eas tareas (compilación) mi programa necesita satisfacer DEPENDENCIAS.. y de paso, maven también me ayuda con eso.

Pero... herramientas como maven a kilos:
- JAVA: MAVEN, GRADLE, SBT
- JS: NPM, YARN, WEBPACK
- C#: NUGET, DOTNET, MSBUILD
