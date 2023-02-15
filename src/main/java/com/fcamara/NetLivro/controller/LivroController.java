package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    /*@GetMapping
    public List<Livro> buscarTodosLivros(@RequestParam(required = false) String nomeLivro) {
        List<Livro> livros;

        if(nomeLivro == null) {
            livros = livroRepository.findAll();
        } else {
            livros = livroRepository.findByTitulo()
        }
    }*/
}
