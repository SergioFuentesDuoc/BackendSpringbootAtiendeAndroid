package com.example.backend.controller;

import com.example.backend.model.TipoUsuario;
import com.example.backend.service.TipoUsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-usuarios")
public class TipoUsuarioController {

    private final TipoUsuarioService service;

    public TipoUsuarioController(TipoUsuarioService service) {
        this.service = service;
    }

    // GET /api/tipo-usuarios
    @GetMapping
    public List<TipoUsuario> getAll() {
        return service.findAll();
    }

    // GET /api/tipo-usuarios/{id}
    @GetMapping("/{id}")
    public TipoUsuario getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // POST /api/tipo-usuarios
    @PostMapping
    public ResponseEntity<TipoUsuario> create(@Valid @RequestBody TipoUsuario tipo) {
        TipoUsuario created = service.create(tipo);
        return ResponseEntity.created(URI.create("/api/tipo-usuarios/" + created.getId())).body(created);
    }

    // PUT /api/tipo-usuarios/{id}
    @PutMapping("/{id}")
    public TipoUsuario update(@PathVariable Integer id, @Valid @RequestBody TipoUsuario tipo) {
        return service.update(id, tipo);
    }

    // DELETE /api/tipo-usuarios/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
