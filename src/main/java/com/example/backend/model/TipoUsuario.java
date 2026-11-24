package com.example.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Tipo de usuario: Administrador, Agente, Supervisor, etc.
 * Equivalente a la entidad Room "tipo_usuarios".
 */
@Entity
@Table(name = "tipo_usuarios")
public class TipoUsuario {

    public static final int MAX_LARGO = 30;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El tipo de usuario es obligatorio")
    @Size(max = MAX_LARGO, message = "El tipo de usuario no puede superar 30 caracteres")
    @Column(name = "tipo_usuario", nullable = false, unique = true, length = MAX_LARGO)
    private String tipoUsuario;

    public TipoUsuario() {}

    public TipoUsuario(Integer id, String tipoUsuario) {
        this.id = id;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTipoUsuario() { return tipoUsuario; }
    public void setTipoUsuario(String tipoUsuario) { this.tipoUsuario = tipoUsuario; }
}
