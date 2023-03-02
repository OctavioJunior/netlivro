package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.exception.InvalidRequestException;
import com.fcamara.NetLivro.model.Exemplar;
import com.fcamara.NetLivro.model.Livro;
import com.fcamara.NetLivro.repository.LivroRepository;

import java.util.Optional;

public class ExemplarForm {
    private Long livroId;

    public Long getLivroId() {
        return livroId;
    }

    public Exemplar converter(LivroRepository livroRepository) {
        Optional<Livro> livro = livroRepository.findById(livroId);
        if(!livro.isPresent()) throw new InvalidRequestException("livro não encontrado");

        return new Exemplar(livro.get());
    }

    public Exemplar atualizar(Exemplar exemplar, LivroRepository repository) {
        Optional<Livro> livro = repository.findById(this.getLivroId());

        if(!livro.isPresent()) throw new InvalidRequestException("livro não encontrado");

        exemplar.setLivro(livro.get());
        return exemplar;
    }
}
