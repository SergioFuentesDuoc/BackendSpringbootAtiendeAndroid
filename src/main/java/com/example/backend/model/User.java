package com.example.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Equivalente a la entidad Room "users".
 * El JSON expone "tipoUsuarioId" igual que en Android; la relacion
 * ManyToOne solo existe para que MySQL cree la FK hacia tipo_usuarios.
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String name;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato valido")
    @Column(nullable = false, unique = true)
    private String email;

    /** Solo para fines academicos (sin hash). No se devuelve en las respuestas. */
    @NotBlank(message = "La contrasena es obligatoria")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false)
    private String password;

    /** Ruta de la foto de perfil. */
    private String profileImageUri;

    /** FK -> tipo_usuarios.id */
    @NotNull(message = "El tipo de usuario es obligatorio")
    @Column(name = "tipo_usuario_id", nullable = false)
    private Integer tipoUsuarioId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_usuario_id", insertable = false, updatable = false)
    private TipoUsuario tipoUsuario;

    public User() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getProfileImageUri() { return profileImageUri; }
    public void setProfileImageUri(String profileImageUri) { this.profileImageUri = profileImageUri; }

    public Integer getTipoUsuarioId() { return tipoUsuarioId; }
    public void setTipoUsuarioId(Integer tipoUsuarioId) { this.tipoUsuarioId = tipoUsuarioId; }
}
