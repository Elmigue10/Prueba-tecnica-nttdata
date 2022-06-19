## Prueba técnica para desarrollador backend

### ¿Qué deseamos evaluarte?

• Creatividad e innovación para resolver el reto.

• Conocimiento en Springboot.

• Conocimiento en Pruebas unitarias.

### ¿Qué se desea?

Implementar una aplicación web orientada a microservicios REST que contenga lo
siguiente.

- CRUD de afiliados.

- CRUD de casos.

- Cobertura de pruebas unitarias al 80%

- Persistencia en base de datos.

- Estrategia de branching.

### Plus del ejercicio

- Api que permita búsqueda por filtros.

- Un filtro que mezcle fechas con la tabla afiliados.

- Manejo de excepciones y errores.

- Documentación de Api ́s con Swagger.

## Solución Propuesta

Para la actividad planteada se decidió desarrollar 2 microservicios, los cuales son los siguientes:

- ms-afiliados: Este microservicio es el encargado de todas las operaciones que se realizan
sobre la tabla de afiliados en la base de datos. Podemos encontrar las operaciones CRUD
(Create, Read, Update, Delete) y algunos filtros de búsqueda como por ejemplo obtener
un afiliado por Id, por numero de identificación, por usuario de creación y por un intervalo
que filtra los afiliados que fueron creados entre una fecha inicial y una fecha final.

- ms-casos: De manera similar al ms-afiliados, este microservicio esta encargado de realizar
todas las consultas y operaciones sobre la tabla de casos en la base de datos. Podemos
realizar operaciones CRUD (Create, Read, Update, Delete) y filtrar nuestras búsquedas por
el Id del caso, usuario de creación, el Id del gestor del caso y de igual manera contamos
con un filtro para obtener los casos cuya fecha de inicio de caso se encuentre entre un
intervalo de una fecha inicial y una fecha final.

Los microservicios están desarrollados con la versión 8 de Java y la versión 2.7.0 de Spring Boot.
Cada uno de los proyectos cuenta con la misma arquitectura, la cual está dividida en 3 capas:

- domain: En la capa de dominio vamos a encontrar todas las clases que van a definir las
estructuras de nuestros objetos principales, es decir, vamos a encontrar las clases que
hacen referencia a las tablas en la base de datos, de igual manera, encontraremos clases
encargadas de la transferencia de información de nuestros objetos, mas conocidos como
DTO, por otro lado, en esta capa se encuentran la estructura de las excepciones que van a
ser lanzadas en nuestra aplicación y por último, vamos a encontrar la interacción con la
base de datos, la cual se hace mediante un interface que implementa los métodos
necesarios para realizar todo tipo de consultas sobre la base de datos.
Es por esta razón que en la capa ‘domain’ vamos a encontrar algunos paquetes como lo
son: entity, dto, exception, repository, entre otros.

<p align="center"> 
<img src="https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Imagenes/domain_package.png">
</p>

- business: La capa ‘business’, es la encargada de toda la lógica de negocio de la aplicación,
es decir, vamos a encontrar todas las funcionalidades que resuelven el problema para el
cual se creó, es decir, se deben realizar validaciones de las reglas de negocio, se deben
realizar flujo de acuerdo con ciertas condiciones cumplidas y se debe realizar el manejo de
errores. Es por esto, que en esta capa vamos a encontrar los controladores de la
aplicación, los cuales son los encargados de exponer rutas en una url, la cual hace
referencia a un único recurso, estas rutas son consumidas por cualquier tipo de cliente,
que necesite hacer uso de los recursos de nuestra aplicación. Y, por otro lado,
encontraremos las clases encargadas de procesar toda esta información que es solicitada a
la aplicación, aplicando las reglas de negocio y validaciones previamente mencionadas,
para de esta manera responder de manera correcta con el recurso que fue solicitado. Los
paquetes que hacen parte de esta capa son los siguientes:

<p align="center"> 
<img src="https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Imagenes/business_package.png">
</p>

- application: En la capa de application, vamos a encontrar clases de configuración de
arranque para nuestra aplicación, estas clases las utilizamos para implementar algunas
características que nos ofrece Spring Boot, en este caso es necesario realizar una
documentación de la aplicación con Swagger, por lo cual, utilizamos una clase de
configuración que nos ayuda a crear una url en nuestro servidor, para poder consultar el
swagger generado automáticamente.

<p align="center"> 
<img src="https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Imagenes/application_package.png">
</p>

### Swagger:

- ms-afiliados: [GitHub (Swagger ms-afiliados).](https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Swagger%20ms-afiliados.yml)
- ms-casos: [GitHub (Swagger ms-casos).](https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Swagger%20ms-casos.yml)

Finalmente, es necesario probar nuestra aplicación, para debemos desarrollar pruebas unitarias que
se encarguen de verificar que cada una de nuestras líneas de código escritas funcionen como lo
deben hacer. JUnit es la tecnología que nos ayuda a escribir y configurar nuestras pruebas unitarias,
estas las podemos encontrar en el paquete de ‘test’ y están separadas para los controladores y para
los servicios, ya que, estos requieren de una configuración diferente, en los controladores se deben
simular peticiones a nuestro servidor y en los servicios, se debe hacer llamado de los métodos
creados. Con alguna herramienta de análisis de código estático, se asegura que las pruebas unitarias
desarrollados cuentan con un porcentaje de cobertura mínimo de 80%, esto para asegurar que
escribimos un código de calidad y que cumple con la función para el cual fue desarrollado.

<p align="center"> 
<img src="https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Imagenes/coverage.png">
</p>

<p align="center"> 
<img src="https://github.com/Elmigue10/Prueba-tecnica-nttdata/blob/feature/documentacion-ms/Documentos/Imagenes/package_coverage.png">
</p>