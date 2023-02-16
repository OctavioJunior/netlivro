package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.controller.form.LivroForm;
import com.fcamara.NetLivro.model.Livro;
import com.fcamara.NetLivro.repository.AutorRepository;
import com.fcamara.NetLivro.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livro")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Livro> buscarTodosLivros(@RequestParam(required = false) String nomeLivro) {
        List<Livro> livros;

        if (nomeLivro == null) {
            livros = livroRepository.findAll();
        } else {
            livros = livroRepository.findByTitulo(nomeLivro);
        }
        return livros;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> buscarLivroPeloID(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (livro.isPresent()) {
            return ResponseEntity.ok(livro.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    ResponseEntity<Livro> criarLivro(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
        Livro livro = form.converter(autorRepository);
            livroRepository.save(livro);
            URI uri = uriBuilder.path("/livro/{id}").buildAndExpand(livro.getId()).toUri();
            return ResponseEntity.created(uri).body(livro);
    }
}
