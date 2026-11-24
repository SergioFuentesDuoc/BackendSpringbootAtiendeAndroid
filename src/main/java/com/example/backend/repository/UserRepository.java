package com.example.backend.repository;

import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAllByOrderByNameAsc();
    Optional<User> findByEmailAndPassword(String email, String password);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Integer id);
    boolean existsByTipoUsuarioId(Integer tipoUsuarioId);
}
