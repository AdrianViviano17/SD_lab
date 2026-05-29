package com.example.biblioteca;

import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/libros")
public class LibroController {

    private List<String> libros = new ArrayList<>();

    @GetMapping
    public List<String> listar() {
        return libros;
    }

    @PostMapping
    public void agregar(@RequestBody String libro) {
        libros.add(libro);
    }

    @GetMapping("/{id}")
    public String buscar(@PathVariable int id) {
        return libros.get(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable int id) {
        libros.remove(id);
    }
}