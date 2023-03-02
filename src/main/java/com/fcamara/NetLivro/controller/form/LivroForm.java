package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.exception.InvalidRequestException;
import com.fcamara.NetLivro.model.Autor;
import com.fcamara.NetLivro.model.Genero;
import com.fcamara.NetLivro.model.Livro;
import com.fcamara.NetLivro.repository.AutorRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class LivroForm {
    @NotNull
    @NotEmpty
    private String titulo;
    @NotNull
    private Long autorId;
    private Genero genero;
    private String descricao;


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Long getAutorId() {
        return autorId;
    }

    public void setAutorId(Long autorId) {
        this.autorId = autorId;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Livro converter(AutorRepository autorRepository) {
        Optional<Autor> autor = autorRepository.findById(autorId);
        if(!autor.isPresent()) throw new InvalidRequestException("autor não encontrado");

        return new Livro(titulo, autor.get(), genero, descricao);
    }

    public Livro atualizar(Livro livro, AutorRepository autorRepository) {
        Optional<Autor> autor = autorRepository.findById(autorId);
        if(!autor.isPresent()) throw new InvalidRequestException("autor não encontrado");

        livro.setAutor(autor.get());
        livro.setTitulo(this.titulo);
        livro.setDescricao(this.descricao);
        livro.setGenero(this.genero);

        return livro;
    }
}

