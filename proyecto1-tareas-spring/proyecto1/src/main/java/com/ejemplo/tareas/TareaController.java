package com.ejemplo.tareas;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TareaController {

    private List<Tarea> tareas = new ArrayList<>();
    private int siguienteId = 1;

    public TareaController() {
        tareas.add(new Tarea(siguienteId++, "Estudiar Spring Boot", false));
        tareas.add(new Tarea(siguienteId++, "Entregar proyecto", false));
    }

    @GetMapping
    public List<Tarea> obtenerTareas() {
        return tareas;
    }

    @GetMapping("/{id}")
    public Tarea obtenerTareaPorId(@PathVariable int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    @PostMapping
    public Tarea crearTarea(@RequestBody Tarea nuevaTarea) {
        nuevaTarea.setId(siguienteId++);
        tareas.add(nuevaTarea);
        return nuevaTarea;
    }

    @PutMapping("/{id}")
    public Tarea actualizarTarea(@PathVariable int id, @RequestBody Tarea tareaActualizada) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                t.setTitulo(tareaActualizada.getTitulo());
                t.setCompletada(tareaActualizada.isCompletada());
                return t;
            }
        }
        return null;
    }

    @PatchMapping("/{id}")
    public Tarea completarTarea(@PathVariable int id) {
        for (Tarea t : tareas) {
            if (t.getId() == id) {
                t.setCompletada(true);
                return t;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String eliminarTarea(@PathVariable int id) {
        tareas.removeIf(t -> t.getId() == id);
        return "Tarea eliminada";
    }
}
