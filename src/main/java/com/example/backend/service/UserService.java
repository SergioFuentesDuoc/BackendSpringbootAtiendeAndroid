package com.example.backend.service;

import com.example.backend.exception.ConflictException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.exception.UnauthorizedException;
import com.example.backend.model.User;
import com.example.backend.repository.TipoUsuarioRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final TipoUsuarioRepository tipoUsuarioRepository;

    public UserService(UserRepository repository, TipoUsuarioRepository tipoUsuarioRepository) {
        this.repository = repository;
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    @Transactional(readOnly = true)
    public List<User> findAll() {
        return repository.findAllByOrderByNameAsc();
    }

    @Transactional(readOnly = true)
    public User findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado: " + id));
    }

    @Transactional(readOnly = true)
    public User login(String email, String password) {
        return repository.findByEmailAndPassword(email, password)
                .orElseThrow(() -> new UnauthorizedException("Email o contrasena incorrectos"));
    }

    @Transactional
    public User create(User user) {
        if (repository.existsByEmailIgnoreCase(user.getEmail())) {
            throw new ConflictException("El email ya esta registrado: " + user.getEmail());
        }
        validarTipoUsuario(user.getTipoUsuarioId());
        user.setId(null);
        return repository.save(user);
    }

    @Transactional
    public User update(Integer id, User data) {
        User user = findById(id);
        if (repository.existsByEmailIgnoreCaseAndIdNot(data.getEmail(), id)) {
            throw new ConflictException("El email ya esta registrado: " + data.getEmail());
        }
        validarTipoUsuario(data.getTipoUsuarioId());
        user.setName(data.getName());
        user.setEmail(data.getEmail());
        user.setPassword(data.getPassword());
        user.setProfileImageUri(data.getProfileImageUri());
        user.setTipoUsuarioId(data.getTipoUsuarioId());
        return repository.save(user);
    }

    @Transactional
    public void delete(Integer id) {
        repository.delete(findById(id));
    }

    private void validarTipoUsuario(Integer tipoUsuarioId) {
        if (!tipoUsuarioRepository.existsById(tipoUsuarioId)) {
            throw new ResourceNotFoundException("Tipo de usuario no encontrado: " + tipoUsuarioId);
        }
    }
}
