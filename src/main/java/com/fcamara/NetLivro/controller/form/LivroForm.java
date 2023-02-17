package com.fcamara.NetLivro.controller.form;

import com.fcamara.NetLivro.model.Autor;
import com.fcamara.NetLivro.model.Genero;
import com.fcamara.NetLivro.model.Livro;
import com.fcamara.NetLivro.repository.AutorRepository;
import com.fcamara.NetLivro.repository.LivroRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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
        Autor autor = autorRepository.getReferenceById(autorId);
        return new Livro(titulo, autor, genero, descricao);
    }

    public Livro atualizar(Long id, LivroRepository livroRepository, AutorRepository autorRepository) {
        Livro livro = livroRepository.getReferenceById(id);
        Autor autor = autorRepository.getReferenceById(autorId);
        livro.setAutor(autor);
        livro.setTitulo(this.titulo);
        livro.setDescricao(this.descricao);
        livro.setGenero(this.genero);

        return livro;
    }
}

