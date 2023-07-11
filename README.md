# Capitole Consulting Challenge

Index
1) [Project structure](#project-structure)
2) [How to run](#how-to-run)
2) [Problem description](#problem-description)

# Project structure

The project follows a DDD (Domain Driven Design) structure, divided into the following three layers:
- **Application**: contains objects related to the web framework, such as Beans, Exception Handler, Rest Controller and DTOs.
- **Domain**: contains our domain classes and the business logic (use cases). Framework agnostic.
- **Infrastructure**: contains classes and logic related to the persistence of our domain entities.

The project is written in Java and uses Spring Boot as the underlying web framework, with H2 as the database. 
It also uses Flyway to manage database migrations.

### Considerations:
- The endpoint is `/prices` with all parameters as query params, as I thought it made the most sense. It can easily be changed to something like `/brands/{brandId}/products/{productId}/prices?date={date}`
if there were more context about the service and the different use cases that it handles.
- The endpoint doesn't have pagination as it only returns one record, but this can be an issue if the dataset
starts to grow, and we have to scan the whole table.
- The method in the repository is only called `findProductPrices` and it uses the Query annotation, because I thought
using JPA naming for the method would end up in a really long name.
- If the service started having more use cases and grew in complexity, and we didn't want to rely on one framework
(be it because we thought it could change in the future), we could divide the application folder
into two folders (application and delivery), and have just the HTTP layer (such as the controller) with the beans in application
whereas in delivery we would have all the presentation logic.

# How to run

You'll first need to install Java 17.

### Running the service with an IDE (i.e. IntelliJ IDEA)

1) Clone the service to your computer using the command below

``git clone git@github.com:sginabreda/capitole_challenge.git``

2) Import the project into the IDE.

3) Execute the main class `com.capitole.challenge.ChallengeApplication.java` to start the service.

### Running the service without an IDE

1) Clone the service to your computer using the command below

``git clone git@github.com:sginabreda/capitole_challenge.git``

2) Go inside the folder containing the project

``cd capitole_challenge``

3) Run the following command to start the application

``./gradlew bootRun`` in Linux

``gradlew.bat bootRun`` in Windows

# Problem Description
En la base de datos de comercio electrónico de la compañía disponemos de la tabla PRICES que refleja el precio final (pvp) y la tarifa que aplica a un producto de una cadena entre unas fechas determinadas. A continuación se muestra un ejemplo de la tabla con los campos relevantes:


PRICES

-------

| BRAND_ID | START_DATE          | END_DATE            | PRICE_LIST | PRODUCT_ID | PRIORITY | PRICE | CURR |
|----------|---------------------|---------------------|------------|------------|----------|-------|------|
| 1        | 2020-06-14-00.00.00 | 2020-12-31-23.59.59 | 1          | 35455      | 0        | 35.50 | EUR  |
| 1        | 2020-06-14-15.00.00 | 2020-06-14-18.30.00 | 2          | 35455      | 1        | 25.45 | EUR  |
| 1        | 2020-06-15-00.00.00 | 2020-06-15-11.00.00 | 3          | 35455      | 1        | 30.50 | EUR  |
| 1        | 2020-06-15-16.00.00 | 2020-12-31-23.59.59 | 4          | 35455      | 1        | 38.95 | EUR  |

Campos:

- **BRAND_ID**: foreign key de la cadena del grupo (1 = ZARA).

- **START_DATE , END_DATE**: rango de fechas en el que aplica el precio tarifa indicado.

- **PRICE_LIST**: Identificador de la tarifa de precios aplicable.

- **PRODUCT_ID**: Identificador código de producto.

- **PRIORITY**: Desambiguador de aplicación de precios. Si dos tarifas coinciden en un rago de fechas se aplica la de mayor prioridad (mayor valor numérico).

- **PRICE**: precio final de venta.

- **CURR**: iso de la moneda.

Se pide:

- Construir una aplicación/servicio en SpringBoot que provea una end point rest de consulta  tal que:
  - Acepte como parámetros de entrada: fecha de aplicación, identificador de producto, identificador de cadena.
  - Devuelva como datos de salida: identificador de producto, identificador de cadena, tarifa a aplicar, fechas de aplicación y precio final a aplicar.
  - Se debe utilizar una base de datos en memoria (tipo h2) e inicializar con los datos del ejemplo, (se pueden cambiar el nombre de los campos y añadir otros nuevos si se quiere, elegir el tipo de dato que se considere adecuado para los mismos).

- Desarrollar unos test al endpoint rest que  validen las siguientes peticiones al servicio con los datos del ejemplo:

  - `Test 1: petición a las 10:00 del día 14 del producto 35455   para la brand 1 (ZARA)`

  - `Test 2: petición a las 16:00 del día 14 del producto 35455   para la brand 1 (ZARA)`

  - `Test 3: petición a las 21:00 del día 14 del producto 35455   para la brand 1 (ZARA)`

  - `Test 4: petición a las 10:00 del día 15 del producto 35455   para la brand 1 (ZARA)`

  - `Test 5: petición a las 21:00 del día 16 del producto 35455   para la brand 1 (ZARA)`

Se valorará:

- Diseño y construcción del servicio.
- Calidad de Código.
- Resultados correctos en los test.