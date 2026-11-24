package com.example.backend.service;

import com.example.backend.exception.ConflictException;
import com.example.backend.exception.ResourceNotFoundException;
import com.example.backend.model.TipoUsuario;
import com.example.backend.repository.TipoUsuarioRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TipoUsuarioService {

    private final TipoUsuarioRepository repository;
    private final UserRepository userRepository;

    public TipoUsuarioService(TipoUsuarioRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<TipoUsuario> findAll() {
        return repository.findAllByOrderByIdAsc();
    }

    @Transactional(readOnly = true)
    public TipoUsuario findById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tipo de usuario no encontrado: " + id));
    }

    @Transactional
    public TipoUsuario create(TipoUsuario tipo) {
        String nombre = tipo.getTipoUsuario().trim();
        if (repository.existsByTipoUsuarioIgnoreCase(nombre)) {
            throw new ConflictException("Ya existe el tipo de usuario: " + nombre);
        }
        return repository.save(new TipoUsuario(null, nombre));
    }

    @Transactional
    public TipoUsuario update(Integer id, TipoUsuario data) {
        TipoUsuario tipo = findById(id);
        String nombre = data.getTipoUsuario().trim();
        if (repository.existsByTipoUsuarioIgnoreCaseAndIdNot(nombre, id)) {
            throw new ConflictException("Ya existe el tipo de usuario: " + nombre);
        }
        tipo.setTipoUsuario(nombre);
        return repository.save(tipo);
    }

    /** RESTRICT: no se puede borrar un tipo que esta en uso por algun usuario. */
    @Transactional
    public void delete(Integer id) {
        TipoUsuario tipo = findById(id);
        if (userRepository.existsByTipoUsuarioId(id)) {
            throw new ConflictException("No se puede eliminar: hay usuarios con este tipo");
        }
        repository.delete(tipo);
    }
}
