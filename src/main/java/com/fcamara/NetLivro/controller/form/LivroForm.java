package com.fcamara.NetLivro.controller.form;

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
    private Genero genero = Genero.AVENTURA;
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
        Autor autor = autorRepository.getReferenceById(autorId);
        return new Livro(titulo, autor, genero, descricao);
    }
}

