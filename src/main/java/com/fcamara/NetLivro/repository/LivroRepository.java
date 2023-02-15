package com.fcamara.NetLivro.repository;

import com.fcamara.NetLivro.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByTitulo(String titulo);
}
