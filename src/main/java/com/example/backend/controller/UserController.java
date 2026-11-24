package com.example.backend.controller;

import com.example.backend.model.LoginRequest;
import com.example.backend.model.User;
import com.example.backend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // GET /api/users
    @GetMapping
    public List<User> getAll() {
        return service.findAll();
    }

    // GET /api/users/{id}
    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id) {
        return service.findById(id);
    }

    // POST /api/users
    @PostMapping
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User created = service.create(user);
        return ResponseEntity.created(URI.create("/api/users/" + created.getId())).body(created);
    }

    // PUT /api/users/{id}
    @PutMapping("/{id}")
    public User update(@PathVariable Integer id, @Valid @RequestBody User user) {
        return service.update(id, user);
    }

    // DELETE /api/users/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    // POST /api/users/login  { "email": "...", "password": "..." }
    @PostMapping("/login")
    public User login(@Valid @RequestBody LoginRequest request) {
        return service.login(request.email(), request.password());
    }
}
