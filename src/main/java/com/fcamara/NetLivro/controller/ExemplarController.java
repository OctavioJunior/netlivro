package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.model.Exemplar;
import com.fcamara.NetLivro.repository.ExemplarRepository;
import com.fcamara.NetLivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exemplar")
public class ExemplarController {
    @Autowired
    private ExemplarRepository exemplarRepository;

    @Autowired
    private LivroRepository livroRepository;

    @GetMapping
    public List<Exemplar> buscarTodosExemplares(@RequestParam(required = false) String tituloLivro) {
        List<Exemplar> exemplar;

        if (tituloLivro == null) {
            exemplar =  exemplarRepository.findAll();
        } else {
            exemplar =  exemplarRepository.findByLivroTituloContaining(tituloLivro);
        }
        return exemplar;
    }

//    @PostMapping
//    @Transactional
//    public ResponseEntity<Exemplar> cadastrarExemplar(@RequestBody @Valid ExemplarForm form){
//
//    }

}
