
# Animalitos Fermín

Que la gente pueda ver los animalitos que tengo en venta
Que mis empleados puedan dar de alta los animalitos


   FRONTAL                                      BACKEND
  -----------------------------------------  --------------------------------------------------------------------------------------------
   App web (JS/TS)                                                                                                            
    Componente WEB <>  Servicio Animalitos <>  Controlador REST <> Servicio de Animalitos <> Repositorio de Animalitos <>  BBDD
    <ficha-animalito
     id="2232"/>
    [mostrar datos      [comunicar con         [exposición de        [lógica de negocio]      [lógica de persistencia]    [persistencia]
     del animal]         backend ]              un servicio                                     public Long persistirAnimalito(Animalito)


   App android (Kotlin)
   App ios (swift)                 < JSON >    /api/v1/animalito/37    dameLosDatosDelAnimalito(37)
   App Alexa 
   App Ok google                                                       altaDeAnimalito(DatosNuevoAnimalito)
   App Siri                                                                 validar los datos
   Ivr                                                                      solicitar su persistencia
   App nativa Windows                                                       solicitar el envío de un email
    componente Formulario
    [capturar datos                                                 Servicio de Envio de Emails
    de un animalito]                                                        mandarEmail(Asunto, Destinatario, Cuerpo)



Voy a desarrollar mi función ServicioDeAnimalitos.altaDeAnimalito(DatosDeAnimalito datosAnimalito);

Qué tipos de pruebas le hago? 
    UNITARIAS?      TODAS
    INTEGRACION?    TODAS
    SISTEMA?        TODAS

Pero tengo un problema.... Puedo hacerle pruebas unitarias tal cuál? Tiene dependencias:
- Servicio de Envío de Emails
- Repositorio de Animalitos
Para hacer la prueba, UNITARIA, necesito aislar a mi componente de esos otros componentes.
```java
public interface DatosDeNuevoAnimalito {
    String getNombre();
    int getTipo();
    String getEdad();
}
public interface Animalito extends DatosDeNuevoAnimalito{
    Long getId();
}
public class ServicioAnimalitos {
    RepositorioDeAnimalitos miRepositorio;
    ServicioDeEmails miServicioDeEmails;

    void configurar(RepositorioDeAnimalitos miRepositorio,  ServicioDeEmails miServicioDeEmails) {
        this.miRepositorio = miRepositorio;
        this.miServicioDeEmails = miServicioDeEmails;
    }

    public Animalito altaDeAnimalito(DatosDeNuevoAnimalito datos){
        // Validaciones
        if(datos.getNombre().isEmpty() || datos.tipo().isEmpty()){
            throw new IllegalArgumentException("Los datos no pueden estar vacíos");
        }
        // Al menos 3 caracteres
        if(datos.getNombre().length() < 3){
            throw new IllegalArgumentException("El nombre debe tener al menos 3 caracteres");
        }
        Long id = miRepositorio.guardarAnimalito(datos);
        miServicioDeEmails.mandarEmail("Nuevo animalito", "Se ha dado de alta un nuevo animalito:" + datos.getNombre(), 
        "subscriptores@animalitosfermin.com");
        return new AnimalitoImpl(id, datos.getNombre(), datos.getTipo(), datos.getEdad());
    }
}
```
## PRUEBAS UNITARIAS DE LA FUNCION altaDeAnimalito
> HAPPY PATH: Comprobar que si llamo a la función con datos correctos:
      se validan los datos sin problema
      se solicita el guardado del animalito
      y se recupera el ID del animalito entregado por el repositorio
      y se solicita que se envíe un email
      y que devuelva el animalito creado
```java
void pruebaAltaAnimalitoHappyPath(){

    String nombre = "Pipo";
    String tipo = "Perro";
    int edad = 3;

    DatosDeNuevoAnimalito datos = new DatosDeNuevoAnimalitoImpl(nombre, tipo, edad);
    ServicioAnimalitos miServicio = new ServicioAnimalitos();
    //ServicioDeEmails espiaDeEmails = new ServicioDeEmailsSpy();
    ServicioDeEmails servicioDeEmailsMock = new ServicioDeEmailsMock();
    // Configuro el mock:
    servicioDeEmailsMock.esperaQueTeLlamenConLosDatos("Nuevo animalito", "Se ha dado de alta un nuevo animalito:" + nombre, 
                                                      "subscriptores@animalitosfermin.com");

    RepositorioDeAnimalitos repoDeAnimalitosMock = new RepositorioDeAnimalitosMock();
    // Configuro el mock:
    repoDeAnimalitosMock.esperaQueTeLlamenConLosDatosYDevuelve(datos, 33L);
    miServicio.configurar(repoDeAnimalitosMock, servicioDeEmailsMock);

    Animalito elAnimalitoDevuelto = miServicio.altaDeAnimalito(datos);

    // Aseguraciones // ASSERTS // Loo que espero que ocurra
    assertEquals(nombre, elAnimalitoDevuelto.getNombre());
    assertEquals(tipo, elAnimalitoDevuelto.getTipo());
    assertEquals(edad, elAnimalitoDevuelto.getEdad());
    assertNotNull(elAnimalitoDevuelto.getId());
    assertEquals(33L, elAnimalitoDevuelto.getId()); // Si se cumple esta condición:
        // 1. Hemos pedido al repo que guarde el animalito
        // 2. Hemos leído el ID que nos ha devuelto

    // Me aseguro que han llamado a la función mandarEmail del servicio de emails (en este caso, mi espía)
    // Me aseguro que le han pasado los parámetros correctos
    //assertEquals("Nuevo animalito", espiaDeEmails.asunto);
    //assertTrue(espiaDeEmails.cuerpo.contains(nombre));
    //assertEquals("subscriptores@animalitosfermin.com", espiaDeEmails.destinatario);
    assertTrue(servicioDeEmailsMock.meHanLlamado);
}

class RepositorioDeAnimalitosMock implements RepositorioDeAnimalitos{
    public DatosDeNuevoAnimalito datos;
    public Long idAdevolver;
    public meHanLlamado = false;
    public void esperaQueTeLlamenConLosDatosYDevuelve(DatosDeNuevoAnimalito datos, Long idAdevolver){
        this.idAdevolver = idAdevolver;
        this.datos = datos;
    }
    public Long guardarAnimalito(DatosDeNuevoAnimalito datos){
        assertEquals(datos, this.datos);
        meHanLlamado = true;
        return idAdevolver;
    }
}
class ServicioDeEmailsSpy implements ServicioDeEmails{
    public String asunto;
    public String cuerpo;
    public String destinatario;
    public void mandarEmail(String asunto, String cuerpo, String destinatario){
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
    }
}
//Un Mock es a un Spy lo que un Fake es a un Stub... Es un spy que tiene dentro la lógica para validar la llamada.

class ServicioDeEmailsMock implements ServicioDeEmails{
    public String asunto;
    public String cuerpo;
    public String destinatario;
    public meHanLlamado = false;
    public void esperaQueTeLlamenConLosDatos(String asunto, String cuerpo, String destinatario){
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.destinatario = destinatario;
    }
    public void mandarEmail(String asunto, String cuerpo, String destinatario){
        assertEquals(asunto, this.asunto);
        assertTrue(this.cuerpo.contains(cuerpo));
        assertEquals(destinatario, this.destinatario);
        meHanLlamado = true;
    }
}



class ServicioDeEmailsDummy implements ServicioDeEmails{
    public void mandarEmail(String asunto, String cuerpo, String destinatario){
        // Claro que si guapi!!!!
    }
}
```

    ComponenteA >>>> ComponenteB
                <<<<

    ServicioDeAnimalitos        RepositorioDeAnimalitos
        altaAnimalito     >>>>       guardarAnimalito       FAKE / MOCKS (SPY + STUB)
                          <<<<

    Lo fakes llevan lógica dentro.
    Los mocks son configurables.
        En ocasiones me creo mis fakes.
        En ocasiones uso librerías para que creen mis mocks

    ServicioDeAnimalitos        ServicioDeEmails
        altaAnimalito     >>>>       mandarEmail            SPY
                          ????

    ServicioDeAnimalitos        OtroEscenario
        altaAnimalito     ????       otroMetodo             STUB
                          <<<<

    Los Stub garantizan que: La función del componente A llama a la función del componente B y lee el resultado
    Los Spy garantizan que:  La función del componente A llama a la función del componente B con los datos correctos

> Probaré a dar de alta un animalito sin nombre, esperando una excepción
> Probaré a dar de alta un animalito con un nombre de 2 caracteres, esperando una excepción
> Probaré a dar de alta un animalito sin Tipo
> Probaré a dar de alta un animalito sin Edad

## Pruebas de integración 1
    ServicioAnimalitosDeVerdadDeLaBuena (el que estoy escribiendo)
        .altaDeAnimalito
    Pasando el RepositorioDeAnimalitosDeVerdadDeLaBuena
        .guardarAnimalito
    Y pasando el ServicioDeEmailsDummy (que compile, que ejecute.. pero que no haga nada... así la prueba va más rápido)
        .mandarEmail

> HAPPY PATH: Comprobar que si llamo a la función con datos correctos:
    // que la rueda se para
    Que si le pido al Repositorio de AnimalitosDe Verdad DeLaBuena el animalito con el ID que
    me haya devuelo la función altaDeAnimalito, me devuelva el animalito cuya creación solicité... con sus datos.
    Que el animalito esté en el REPORITORIO DE ANIMALITOS DE VERDAD DE LA BUENA

## Pruebas de integración 2
    ServicioAnimalitosDeVerdadDeLaBuena (el que estoy escribiendo)
        .altaDeAnimalito
    Pasando el RepositorioDeAnimalitosStub (que devuelva 33)... y no comprueba nada
        .guardarAnimalito
    Y pasando el ServicioDeEmailsDeVerdadDeLaBuena
        .mandarEmail

> HAPPY PATH: Comprobar que si llamo a la función altaDeAnimalito con datos correctos:
    QUE TIENE QUE OCURRIR SI LA INTEGRACION CON EL SISTEMA DE EMAILS ES BUENA?
    - Que en mi bandeja de entrada IMAP de "subscriptores@animalitosfermin.com"
        Haya un email con los datos que he solicitado que se envíen: NOMBRE DEL ANIMALITO Y EL ASUNTO: "Nuevo animalito"

## Pruebas de sistema
    ServicioAnimalitosDeVErdadDeLaBuena (el que estoy escribiendo)
        .altaDeAnimalito
    Pasando el RepositorioDeAnimalitosDeVerdadDeLaBuena
        .guardarAnimalito
    Y pasando el ServicioDeEmailsDeVerdadDeLaBuena
        .mandarEmail        

> HAPPY PATH: Comprobar que si llamo a la función altaDeAnimalito con datos correctos:
    Que el animalito esté en el REPORITORIO DE ANIMALITOS DE VERDAD DE LA BUENA
    Que en mi bandeja de entrada IMAP de "subscriptores@animalitosfermin.com"
        Haya un email con los datos que he solicitado que se envíen: NOMBRE DEL ANIMALITO Y EL ASUNTO: "Nuevo animalito"

---

# Componente web?

<formulario-animalito-de-fermin id=2239483/>

Hoy en día, los navegadores de forma nativa me permiten definir mis propias marcas HTML.
Y dotarlas de lógica y estética.

    React
    Angular
    Vue
    LitElement


---

# MAVEN

Herramienta de automatización de trabajos.
Los trabajos que automatizo se denominan: GOALS

Los GOALS son ejecutados por plugins... SIEMPRE
En el archivo pom, configuramos los plugins que vamos a usar.
PLUGINS                 GOALS
- clean                 clean
- resources             resources ; test-resources
- compile               compile ; test-compile
- surefire              test
- jar                   package

En maven ya vienen unos cuantos cargados por defecto... (de hecho los tendremos que actualizar)
Esos que vienen ya me ejecutan algunos GOALS típicos de un proyecto de Java:
- compile               Compila las clases que tengo en src/main/java... y 
                        deja los.class en la carpeta target/classes
                        + copia los archivos de recursos de src/main/resources en target/classes
- test-compile          Compila las clases que tengo en src/test/java... y 
                        deja los.class en la carpeta target/test-classes
                        + copia los archivos de recursos de src/test/resources en target/test-classes
- test                  Ejecuta las pruebas que tengo en target/test-classes
                        Quien ejecuta las pruebas es el plugin surefire 
                        Adicionalmente el plugin surefire me genera un informe de pruebas
                        Se guarda en target/surefire-reports
- package               Empaqueta el proyecto... en función del empaquetado que defina en el pom.xml
                        - jar   Empaqueta lo que tengo en target/classes en un .jar
                                que se deja en target 
- install               Copiar el archivo .jar en mi carpeta .m2
                        Si necesito que mi proyecto sea dependencia de otro proyecto... si no... pa que?
- clean                 Borra la carpeta target

La carpeta .m2 de maven es una carpeta que se guarda en mi carpeta de usuario.
En esa carpeta maven descarga de internet las dependencias que mis proyectos necesitan.

proyecto/
    src/
        main/
            java/           Meto mis clases... Las que distribuyo como parte de mi distribución
            resources/      Meto son archivos de configuración, de propiedades, de imágenes, de plantillas
                            que también se distribuyen con mi aplicación
        test/
            java/           Meto mis clases de pruebas...
            resources/      Meto los archivos de configuración de pruebas
    target/
            classes/        Aquí se dejan los .class de las clases que he compilado
            test-classes/   Aquí se dejan los .class de las clases de pruebas que he compilado
            target/surefire-reports
                            Archivos xml con los resultados de las pruebas
                                ^^^ Estos archivos son los que lee un programa como JENKINS para saber si las pruebas han pasado o no
    pom.xml                 Archivo de configuración de maven
