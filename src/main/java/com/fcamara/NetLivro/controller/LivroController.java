package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.controller.form.LivroForm;
import com.fcamara.NetLivro.model.Livro;
import com.fcamara.NetLivro.repository.AutorRepository;
import com.fcamara.NetLivro.repository.LivroRepository;
import com.sun.javaws.exceptions.InvalidArgumentException;
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
            livros = livroRepository.findByTituloContaining(nomeLivro);
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
    ResponseEntity criarLivro(@RequestBody @Valid LivroForm form, UriComponentsBuilder uriBuilder) {
        try {
            Livro livro = form.converter(autorRepository);
            livroRepository.save(livro);
            URI uri = uriBuilder.path("/livro/{id}").buildAndExpand(livro.getId()).toUri();
            return ResponseEntity.created(uri).body(livro);
        } catch (InvalidArgumentException e) {
            return ResponseEntity.badRequest().body(e.getField());
        }
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarLivro(@PathVariable Long id, @RequestBody @Valid LivroForm form){
        Optional<Livro> livro = livroRepository.findById(id);

        if(livro.isPresent()){
            try {
                form.atualizar(livro.get(), autorRepository);
                return ResponseEntity.ok(livro.get());
            } catch (InvalidArgumentException e) {
                return ResponseEntity.badRequest().body(e.getField());
            }
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerLivro(@PathVariable Long id){
        Optional<Livro> optional = livroRepository.findById(id);

        if (optional.isPresent()){
            livroRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}

