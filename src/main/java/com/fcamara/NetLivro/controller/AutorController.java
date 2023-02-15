package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.controller.form.AutorForm;
import com.fcamara.NetLivro.model.Autor;
import com.fcamara.NetLivro.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Autor> buscarTodosAutores(String nomeAutor){
        if(nomeAutor == null) {
            return autorRepository.findAll();
        } else {
            return autorRepository.findByNomeContaining(nomeAutor);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutorPeloID(@PathVariable Long id){
        Optional<Autor> optional = autorRepository.findById(id);

        if(optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return  ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Autor> criarAutor(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder ){
        Autor autor = form.converter();
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
        autorRepository.save(autor);
        return ResponseEntity.created(uri).body(autor);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorForm form) {
        Optional<Autor> optional = autorRepository.findById(id);

        if(optional.isPresent()) {
            Autor autor = form.atualizar(id, autorRepository);

            return ResponseEntity.ok(autor);
        }

        return  ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerAutor(@PathVariable Long id) {
        Optional<Autor> optional = autorRepository.findById(id);

        if(optional.isPresent()) {
            autorRepository.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return  ResponseEntity.notFound().build();
    }
}
