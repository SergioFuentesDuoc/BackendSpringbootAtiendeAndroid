package com.example.backend.model;

import jakarta.validation.constraints.NotBlank;

/** Cuerpo de la peticion de login: { "email": "...", "password": "..." } */
public record LoginRequest(
        @NotBlank String email,
        @NotBlank String password
) {}
