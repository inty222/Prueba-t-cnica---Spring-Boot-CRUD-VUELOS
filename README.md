CRUD Vuelos

Esta es una aplicación hecha en Spring Boot que sirve para gestionar vuelos. Con ella puedes crear, leer, actualizar y eliminar vuelos de forma sencilla usando una API REST.

Qué se puede hacer

Listar todos los vuelos.

Filtrar vuelos por empresa, lugar de llegada o fecha de salida.

Ordenar la lista de vuelos por empresa o lugar de llegada.

Crear un nuevo vuelo.

Actualizar un vuelo existente.

Eliminar un vuelo.

Tecnologías usadas

Java 17

Spring Boot

Maven

Postman (para probar la API)

Lombok (para simplificar el código)

Cómo probar la aplicación

Abrir el proyecto en IntelliJ.

Ejecutar la aplicación desde la clase CrudVuelosApplication.

Abrir Postman y probar los endpoints:

GET /vuelos → listar todos los vuelos.

GET /vuelos/{id} → obtener un vuelo por su ID.

POST /vuelos → crear un vuelo.

PUT /vuelos/{id} → actualizar un vuelo.

DELETE /vuelos/{id} → eliminar un vuelo.

También se puede filtrar y ordenar usando parámetros en la URL, por ejemplo:

GET [/vuelos?ordenarPor=empresa](http://localhost:8080/vuelos?ordenarPor=lugarLegada)

Notas

Se validan los vuelos al crearlos o actualizarlos: no se permiten campos vacíos y la fecha de salida no puede ser mayor que la de llegada.

Todos los datos se almacenan en memoria mientras la aplicación está corriendo.
