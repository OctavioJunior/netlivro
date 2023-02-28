package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.model.Emprestimo;
import com.fcamara.NetLivro.repository.EmprestimoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping
    public List<Emprestimo> listarTodosEmprestimos(Boolean disponivel) {
        List<Emprestimo> emprestimos;

        if(disponivel == null) {
             emprestimos = emprestimoRepository.findAll();
        } else {
            emprestimos = emprestimoRepository.findByExemplarDisponivel(disponivel);
        }

        return emprestimos;
    }

    /*@PostMapping
    @Transactional
    public ResponseEntity<Emprestimo> criarEmprestimo(EmprestimoForm form) {

    }*/
}
