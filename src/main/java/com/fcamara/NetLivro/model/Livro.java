package com.fcamara.NetLivro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Livro {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @ManyToOne @JsonIgnore
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    private String descricao;
    @OneToMany(mappedBy = "livro")
    private List<Exemplar> exemplares = new ArrayList<>();

    public Livro() {
    }

    public Livro(String titulo, Autor autor, Genero genero, String descricao) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId( Long id ) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo( String titulo ) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor( Autor autor ) {
        this.autor = autor;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero( Genero genero ) {
        this.genero = genero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao( String descricao ) {
        this.descricao = descricao;
    }

    public List<Exemplar> getExemplares() {
        return exemplares;
    }

    public void setExemplares(List<Exemplar> exemplares) {
        this.exemplares = exemplares;
    }
}
