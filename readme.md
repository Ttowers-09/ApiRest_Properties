# Creacion del repositorio utilizando Spring Initializr:
- Definimos las caracteristicas:
    - Project: Maven.
    - Language: Java.
    - Spring Boot: 3.5.6
    - Project Metadata:
        - Group: com.properties.
        - Artefact: manage-properties.
        - Name: manage-properties.
        - Package name: com.properties.manage_properties.
    - Java: 21
    - Dependencies:
        - Spring Web:
        - Spring Data JPA:
        - Spring Boot DevTools:
        - Lombok:
        - Validation:
        - MySQL Driver:

# Arquitectura de nuestro Backend:

- **model (Clase):**
    - Acá definimos el "molde" de lo que vamos a utilizar en este caso son propiedades, allí implementamos las características (atributos), getters, setters y constructores.

- **repository (Interfaz):**
    - Capa que tiene comunicación con la base de datos utilizando JPA.
    - Acá se dan los CRUD básicos como save, findById, findAll, deleteById.
    - Podemos agregar métodos que consideremos que serán utiles en un futuro.

- **Service:**
    - Acá se definen las reglas de negocio.

- **Controller:**
    - recibe las órdenes del usuario y se encarga de solicitar los datos al modelo y de comunicárselos a la vista.

# Models:
## Property.java: Acá definimos el modelo de nuestras propiedades para nuestra API.

Acá vamos a encontrar nuestra clase base alli vamos a encontrar los atributos:

- id.
- address (String).
- price (Integer).
- size (Integer).
- dsescription (String).
- actualOwner (String).

Encontramos validaciones como:

- @NotBlank -> Aplica para Strings.
- @Size(max = 100)
- @Column(nullable = false , length = 100) -> nullable  para que no sea nulo y length para su longitud.
- @NotNull -> Aplica para números.
- @Positive -> Aplica para números.

Diferencia entre Size y length.
- Size es una regla, si la regla no se cumple el registro no se guarda, length es para el tamaño.

utilizamos el plugin de lombook para evitar realizar getters, setters y constructores, la notación de estos es la siguiente:

- @Getter 
- @Setter
- @NoArgsConstructor 
- @AllArgsConstructor 
- @Builder

# Repository:

- Colocamos funciones adicionales que se encuentran en nuestros requerimientos.

    - **List <Property> findByLocation (String location);**
        - Devuelve una lista de propiedades que se encuentran en una locación.
    - **List <Property> findPropertyBetwwenPrice (int minPrice, int maxPrice);**
        - Devuelve una lista de propiedades que se encuentran entre un precio minimo y un precio maximo.
    - **List <Property> findPropertyBetweenSize (Integer minSize, Integer maxSize);**
        - Devuelve una lista de propiedades que se encuentran entre un tamaño minimo y un tamaño maximo.

# Service:
- Primero declaramos como final la variable que representará a nuestro repositorio.

- tenemos los siguiente métodos:
    - **Property createProperty (Property property):** El cual crea una nueva propiedad y se invoca al repositorio con la función save.
    - **List <Property> findByLocation (String location):** Devuelve una lista de las propiedades que se encuentran en esa locación, invoca al repositorio con el método findByLocation.
    - **List <Property> findAll:** Devuelve una lista con todas las propiedades, invoca al repositorio con el método findAll.
    - **Property findById (Long id):** Devuelve una propiedad mediante el Id, invoca al repositorio mediante la función findById.
    - **void deleteById (Long id):** Elimina una propiedad mediante el id, invoca al repositorio mediante dos funciones la primera findById para validar la propiedad y la segunda deleteById.
    - **List <Property> findPropertyBetwwenPrice:** Devuelve una lista con las propiedades que se encuentran en un rango de precios, invoca al repositorio con la función findPropertyBetwwenPrice.
    - **Property updateProperty (Long id, Property property):** actualiza los campos de las propiedades, invoca el repositorio con la funcion findById para verificar la existencia, luego save para guardar los cambios.

# Controller
