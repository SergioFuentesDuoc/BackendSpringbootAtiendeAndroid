package com.example.backend.repository;

import com.example.backend.model.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoUsuarioRepository extends JpaRepository<TipoUsuario, Integer> {
    List<TipoUsuario> findAllByOrderByIdAsc();
    boolean existsByTipoUsuarioIgnoreCase(String tipoUsuario);
    boolean existsByTipoUsuarioIgnoreCaseAndIdNot(String tipoUsuario, Integer id);
}
