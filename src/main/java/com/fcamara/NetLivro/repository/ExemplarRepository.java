package com.fcamara.NetLivro.repository;

import com.fcamara.NetLivro.model.Exemplar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExemplarRepository extends JpaRepository<Exemplar, Long> {
    List<Exemplar> findByLivroTituloContaining(String tituloLivro);
}
