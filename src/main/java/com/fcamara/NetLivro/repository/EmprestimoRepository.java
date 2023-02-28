package com.fcamara.NetLivro.repository;

import com.fcamara.NetLivro.model.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByExemplarDisponivel(boolean disponivel);
}
