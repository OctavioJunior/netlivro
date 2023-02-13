package com.fcamara.NetLivro.repository;

import com.fcamara.NetLivro.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNomeContaining( String nomeAutor );

}


