# Proyecto de tareas con Spring Boot

Este es un proyecto chiquito hecho en Java con Spring Boot. Es una API para manejar una lista de tareas (como un to-do list) usando los 5 verbos HTTP: GET, POST, PUT, PATCH y DELETE.

## Qué hace

Cada tarea tiene un id, un titulo y si esta completada (true o false). Las tareas se guardan en una lista dentro del programa, no en una base de datos, o sea que si apagas el programa se borran. Se hizo así para que fuera lo más simple posible.

## Archivos del proyecto

- **TareasApplication.java**: el archivo que prende el programa.
- **Tarea.java**: aquí se define cómo es una tarea (sus datos).
- **TareaController.java**: aquí están los endpoints, o sea las URLs que responden.

## Para qué sirve cada anotación

- `@SpringBootApplication`: dice que aquí arranca el proyecto.
- `@RestController`: dice que esta clase va a contestar peticiones web con datos (JSON).
- `@RequestMapping("/tareas")`: todas las URLs de aquí empiezan con /tareas.
- `@PathVariable`: agarra un valor que viene en la URL, como el id.
- `@RequestBody`: agarra los datos que se mandan en formato JSON.

## Los endpoints

- **GET /tareas** — trae todas las tareas.
- **GET /tareas/{id}** — trae una sola tarea buscándola por su id.
- **POST /tareas** — crea una tarea nueva.
- **PUT /tareas/{id}** — cambia el título y el estado de una tarea que ya existe.
- **PATCH /tareas/{id}** — solo marca la tarea como completada, sin tocar lo demás.
- **DELETE /tareas/{id}** — borra una tarea.

La diferencia entre PUT y PATCH es que PUT cambia todo el registro y PATCH solo cambia una parte.

## Cómo correrlo

1. Necesitas Java 17 y Maven instalados.
2. Abres una terminal en la carpeta del proyecto.
3. Escribes:
```
mvn spring-boot:run
```
4. Se abre en http://localhost:8080

## Cómo probar que funciona

Solo de ejemplo, para crear una tarea nueva con Postman o con curl:
```
curl -X POST http://localhost:8080/tareas -H "Content-Type: application/json" -d "{\"titulo\":\"Nueva tarea\",\"completada\":false}"
```
