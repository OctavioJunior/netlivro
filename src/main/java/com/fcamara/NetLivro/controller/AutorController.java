package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.exception.ResourceNotFoundException;
import com.fcamara.NetLivro.controller.form.AutorForm;
import com.fcamara.NetLivro.model.Autor;
import com.fcamara.NetLivro.repository.AutorRepository;
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
@RequestMapping("/autor")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public List<Autor> buscarTodosAutores(String nomeAutor) {
        if (nomeAutor == null) {
            return autorRepository.findAll();
        } else {
            return autorRepository.findByNomeContaining(nomeAutor);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Autor> buscarAutorPeloID(@PathVariable Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if(!autor.isPresent()) throw new ResourceNotFoundException("autor não encontrado");

        return ResponseEntity.ok(autor.get());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Autor> criarAutor(@RequestBody @Valid AutorForm form, UriComponentsBuilder uriBuilder) {
        Autor autor = form.converter();
        URI uri = uriBuilder.path("/autor/{id}").buildAndExpand(autor.getId()).toUri();
        autorRepository.save(autor);
        return ResponseEntity.created(uri).body(autor);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Autor> atualizarAutor(@PathVariable Long id, @RequestBody @Valid AutorForm form) {
        Optional<Autor> autor = autorRepository.findById(id);
        if(!autor.isPresent()) throw new ResourceNotFoundException("autor não encontrado");

        form.atualizar(autor.get());

        return ResponseEntity.ok(autor.get());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerAutor(@PathVariable Long id) {
        Optional<Autor> optional = autorRepository.findById(id);
        if(!optional.isPresent()) throw new ResourceNotFoundException("autor não encontrado");

        autorRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
