package com.fcamara.NetLivro.controller;

import com.fcamara.NetLivro.config.exception.ResourceNotFoundException;
import com.fcamara.NetLivro.controller.form.ExemplarForm;
import com.fcamara.NetLivro.model.Exemplar;
import com.fcamara.NetLivro.repository.ExemplarRepository;
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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarExemplar(@RequestBody @Valid ExemplarForm form, UriComponentsBuilder uriBuilder){
       Exemplar exemplar = form.converter(livroRepository);

       exemplarRepository.save(exemplar);

       URI uri = uriBuilder.path("/exemplar/{id}").buildAndExpand(exemplar.getId()).toUri();
       return ResponseEntity.created(uri).body(exemplar);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizarExemplar(@PathVariable Long id, @RequestBody @Valid ExemplarForm form) {
        Optional<Exemplar> exemplar = exemplarRepository.findById(id);
        if(!exemplar.isPresent()) throw new ResourceNotFoundException("exemplar não encontrado");

        form.atualizar(exemplar.get(), livroRepository);
        exemplarRepository.save(exemplar.get());

        return ResponseEntity.ok().body(exemplar.get());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity removerExemplar(@PathVariable Long id) {
        Optional<Exemplar> exemplar = exemplarRepository.findById(id);
        if(!exemplar.isPresent()) throw new ResourceNotFoundException("exemplar não encontrado");

        exemplarRepository.delete(exemplar.get());
        return ResponseEntity.ok().build();
    }

}
