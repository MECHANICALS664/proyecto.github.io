# Proyecto - Controlador de Tareas con Spring Boot

Este proyecto es una API REST muy sencilla hecha con Java y Spring Boot. Sirve para manejar una lista de "tareas" (como un to-do list) usando los 5 verbos HTTP principales: GET, POST, PUT, PATCH y DELETE.

## ¿Qué hace el proyecto?

Es un sistema básico de tareas. Cada tarea tiene:
- `id`: número que identifica la tarea
- `titulo`: texto que describe la tarea
- `completada`: true o false, si ya se hizo o no

Las tareas no se guardan en una base de datos, se guardan en una lista dentro del programa (una `ArrayList`). Esto significa que si apagas el programa, se pierden. Se hizo así para que sea lo más simple posible.

## Estructura del proyecto

- **TareasApplication.java**: es el archivo que arranca el programa. Todo proyecto de Spring Boot necesita uno.
- **Tarea.java**: es el "molde" de una tarea. Aquí se definen sus datos (id, titulo, completada) y sus getters/setters (métodos para leer y modificar esos datos).
- **TareaController.java**: aquí están los endpoints, o sea, las URLs a las que se les puede pedir información o mandarles información.

## Explicación de las anotaciones usadas

- `@SpringBootApplication`: le dice a Java "esta es la clase principal, desde aquí arranca todo el proyecto de Spring".
- `@RestController`: le dice a Spring que esta clase va a responder peticiones web (HTTP) y que las respuestas van a ser datos (JSON), no páginas HTML.
- `@RequestMapping("/tareas")`: define la URL base. Todos los endpoints de este controlador empiezan con `/tareas`.
- `@PathVariable`: sirve para tomar un valor que viene en la URL, por ejemplo el `id` en `/tareas/3`.
- `@RequestBody`: sirve para tomar los datos que el usuario manda en formato JSON cuando hace un POST o un PUT.

## Explicación de cada endpoint (verbo por verbo)

### GET - Obtener información
- `GET /tareas` → devuelve la lista completa de tareas.
- `GET /tareas/{id}` → devuelve una sola tarea, buscándola por su id. Ejemplo: `GET /tareas/1`.

El verbo GET se usa cuando solo quieres **leer/consultar** información, no cambiar nada.

### POST - Crear información nueva
- `POST /tareas` → crea una tarea nueva. Se le manda un JSON con el título y si está completada o no, por ejemplo:
```json
{
  "titulo": "Lavar el carro",
  "completada": false
}
```
El controlador le pone automáticamente un id nuevo y la agrega a la lista.

El verbo POST se usa cuando quieres **agregar algo nuevo** que antes no existía.

### PUT - Actualizar todo un registro
- `PUT /tareas/{id}` → reemplaza los datos de una tarea existente (título y estado de completada). Se manda un JSON igual que en el POST.

El verbo PUT se usa cuando quieres **actualizar/reemplazar completamente** un dato que ya existe.

### PATCH - Actualizar solo una parte
- `PATCH /tareas/{id}` → en este proyecto, solo marca la tarea como completada (`completada = true`). No hace falta mandar nada en el body.

El verbo PATCH se usa cuando quieres **modificar solo una parte** de un dato, no todo el registro completo (a diferencia de PUT).

### DELETE - Eliminar información
- `DELETE /tareas/{id}` → busca la tarea por id y la borra de la lista.

El verbo DELETE se usa para **eliminar** un dato que ya no quieres que exista.

## Cómo correr el proyecto

1. Tener instalado Java 17 y Maven.
2. Abrir una terminal en la carpeta del proyecto.
3. Ejecutar:
```
mvn spring-boot:run
```
4. El proyecto corre en `http://localhost:8080`.

## Cómo probar los endpoints

Se puede usar Postman, Insomnia, o el comando `curl` desde la terminal. Ejemplos:

```
curl http://localhost:8080/tareas
curl http://localhost:8080/tareas/1
curl -X POST http://localhost:8080/tareas -H "Content-Type: application/json" -d "{\"titulo\":\"Nueva tarea\",\"completada\":false}"
curl -X PUT http://localhost:8080/tareas/1 -H "Content-Type: application/json" -d "{\"titulo\":\"Tarea editada\",\"completada\":true}"
curl -X PATCH http://localhost:8080/tareas/1
curl -X DELETE http://localhost:8080/tareas/1
```
